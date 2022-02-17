# Mxx Compiler 实现说明

## 代码风格

- 遵循 Java 命名规范
- 关于多态
  - 减少多态使用，可以减少文件数量以使得源代码文件结构更为简洁
  - 多态类代码量较少，可直接使用枚举实现多态，例如 `frontend.ast.node.NodeExpression`


## 相关工具

- 代码中部分注释使用 IDEA 插件 [Comments Highlighter](https://plugins.jetbrains.com/plugin/12895-comments-highlighter) 提供高亮
- 代码量统计使用 IDEA 插件 [Statistic](https://plugins.jetbrains.com/plugin/4509-statistic) 完成, 统计方式为计算由本人创建 / 修改的文件中的代码总行数 (纯空行与注释行不计) 



## 具体实现备注

> 关于 g4 文件中的 program & programSection 节点\
> 题面 13.4 要求: 全局变量和局部变量不支持前向引用\
> 故不能将 programSection 规则直接在 program 中展开\
> 13.5: 函数和类的声明都应该在顶层，作用域为全局，支持前向引用（forward reference）



## AST 结构

> 在设计 AST 的过程中，尽量将简单的节点规则直接在父节点规则中展开，避免整体结构过于冗长，同时减少工作量。\
> 在设计 g4 语法的过程中，尽量避免 Parser 部分过多实现 Semantic 功能。

- Root
  - ProgramSection[]
    - `Class Define`
      - Name (String), Method Define[], Variable Define[]
    - `Function Define`
    - `Variable Define`
- Variable Define
  - Type, Variable Term[]
    - Variable Term: Name, Initial Expression
- Function Define
  - Type, Name, Argument List, Suite
    - Argument List: Type[], Identifier[]
- Type
  - Type, Bracket[]
    - Bracket: Expression
- Suite
  - Statement[]
    - `Suite`: Suite
    - `If`: CondExpr, TrueBranchStmt, FalseBranchStmt
    - `For`: InitExpr/InitVarDef, CondExpr, StepExpr, BodyStmt
    - `While`: CondExpr, BodyStmt
    - `Continue / Break / Return`
    - `Variable Define`
    - `Expression`
      - Paren `(expression)`
      - Member `expression.IDENTIFIER`
      - Array `expression[expression]`
      - Function `expression(expressionList)`
      - Assign `expression = expression`
      - New `NEW type`
      - Arith
        - Self `expression op`
          - op: `++` | `--`
        - Unary `op expression`
          - op: `!` | `~`
        - Binary `expression op expression`
          - op: `+` | `-` | `*` | `/` | `%` | `<<` | `>>`
          - op: `>` | `<` | `>=` | `<=` | `==` | `!=`
          - op: `&` | `|` | `~` | `&&` | `||`
      - Atom
        - This
        - Identifier
        - String constant
        - Decimal integer
        - `true` | `false`
        - NULL
    - `Empty`: (only `;`)

### 其他说明

- `AstNode`
  - 规避了 `Accept()` 函数的使用必要
- `AstVisitor`
  - 这是个很愚蠢的东西, 见上 `Accept()`
- 暂时参见 `SemanticChecker.java`
- 由于题面理解有误，一开始的设计中并没有左值相关的信息。导致最后对 Semantic Check 部分进行了一波**很丑很丑**的修改，且难以保证正确性



## IR

- Top

  - Declare

  - Variable Define

    - > global / const / alloca，其中 global 和 const 有初值

  - Type

    - > Struct 定义

  - Function

    - Block
      - Statement
        - Variable Define
        - Load / Store
        - Arith
        - Logic
        - Branch
        - Call

### 其他说明

- 实现 llvm
  - 指令说明详见 https://llvm.org/docs/LangRef.html
- 使用 clang
  - `clang -S -emit-llvm test.c` 生成 `.ll`
  - `clang -c -emit-llvm test.c` 生成 `.bc`
  - 对于 RISC-V32（限制指令集对内容基本没有影响）
    - `clang -emit-llvm -S test.c -o test.ll --target=riscv32` 生成 `.ll`
    - `llc test.ll -o test.s -march=riscv32` 生成 `.s`
- `string` 相关操作应在 codegen 阶段直接将 `cstring` 函数 link 入汇编程序。`malloca` 同理
- `.LL` 文件头
  - `e`: 小端序
  - `m:o`: 符号表 name mangling 的格式
  - `i64:64`: 将`i64`类型的变量采用64比特的ABI对齐
  - `n8:16:32:64`: 目标CPU的原生整型包含 8 比特、16 比特、32 比特和 64 比特
  - `S128`: 栈以128比特自然对齐
- 与 Clang 生成 IR 相比
  - Clang 信息
    - `clang version 10.0.0-4ubuntu1`
    - `Target: x86_64-pc-linux-gnu`
    - `Thread model: posix`
  - 忽略 `dso_local` 及 `.ll` 文件末参数
  - 忽略 `align 4`
- 由于 IR 结构的特性，数据结构主要使用 `LinkedList`
  - 其中对应 C++ `queue.push` 的操作，在 Java 中为 `add`
  - 在 Java 中 `push` 为 `Stack` 的操作，对于 `LinkedList` 等效于 `addFirst`
  - C++ `stack.top` 在 Java 中对应 `Deque.peek` 和 `Deque.getFirst` 后者在空时抛出异常
- 变量定义的时候进行 alloca
  - 使用变量时从内存读出
  - 赋值时写入至内存
- 所需 LLVM IR 变量类型仅有 I1，I32，组合
- LLVM IR 函数 Attribute 不占命名序号，可自由命名（纯数字）。函数开头必为 Label `0:` 且可缺省
- 实现 `toString()` 的对象与字符串进行运算时可以省略该函数调用
- LLVM IR 指令中类型指操作数的类型，在绝大多数情况下等同于指令结果的类型，但 `icmp` 指令除外，例如 `icmp eq i32 %1, %2`，该指令返回值始终为 `i1`
- LLVM IR `phi` 指令支持大于等于 2 个块的情况，但当前仅实现 2 个块
- 类中 `this` 指针作为不可更改的变量出现，故在 IR 中该变量被访问时无需 `load` 而可直接使用

