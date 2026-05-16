# 01. Java 시작하기

Java 는 1995년 발표 이후 30년째 산업 현장의 주력 언어로 쓰이는 정적 타입·객체지향 프로그래밍 언어입니다. 이번 편에서는 JDK 21 LTS 를 설치하고, IDE 를 정한 뒤, 첫 번째 프로그램을 컴파일·실행해 봅니다.

## 학습 목표

- JDK 21 LTS 를 설치하고 `java`, `javac` 명령을 확인한다
- IntelliJ IDEA Community 또는 VS Code 중 하나를 골라 환경을 갖춘다
- `public class` / `public static void main` 의 구조를 이해한다
- `javac` 로 컴파일하고 `java` 로 실행할 수 있다

## 핵심 개념

### 1) JDK 와 JRE

Java 코드를 작성하고 실행하려면 **JDK(Java Development Kit)** 가 필요합니다. JDK 안에는 컴파일러(`javac`), 실행기(`java`), 표준 라이브러리, JRE(Java Runtime Environment) 가 모두 포함되어 있습니다. 우리는 **Eclipse Temurin JDK 21 LTS** 를 권장합니다.

```bash
java --version    # openjdk 21.x.x
javac --version   # javac 21.x.x
```

### 2) 운영체제별 JDK 21 설치

다운로드 페이지: <https://adoptium.net/temurin/releases/?version=21>

#### Windows

1. Temurin 사이트에서 **`OpenJDK 21 - Windows x64 .msi`** 를 받아 더블 클릭으로 설치
2. 설치 마법사에서 **"Set JAVA_HOME variable"** 과 **"Add to PATH"** 옵션 체크
3. 기본 설치 경로:
   ```
   C:\Program Files\Eclipse Adoptium\jdk-21.x.x-hotspot\
   ```
4. PowerShell 을 새로 열고 확인:
   ```powershell
   $env:JAVA_HOME
   # C:\Program Files\Eclipse Adoptium\jdk-21.x.x-hotspot
   java --version
   ```
5. 수동 설정이 필요하면 `시스템 속성 → 고급 → 환경 변수` 에서
   - `JAVA_HOME` = `C:\Program Files\Eclipse Adoptium\jdk-21.x.x-hotspot`
   - `Path` 에 `%JAVA_HOME%\bin` 추가

#### macOS

옵션 A — **Homebrew** (권장):

```bash
brew install --cask temurin@21
```

설치 경로:

```
/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home
```

`~/.zshrc` 에 다음을 추가하고 `source ~/.zshrc`:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
export PATH="$JAVA_HOME/bin:$PATH"
```

옵션 B — Temurin 사이트에서 **`.pkg`** 받아 설치 (위와 같은 경로에 설치됨).

#### Linux (Ubuntu/Debian)

옵션 A — **apt** 로 OpenJDK 21:

```bash
sudo apt update
sudo apt install -y openjdk-21-jdk
```

설치 경로:

```
/usr/lib/jvm/java-21-openjdk-amd64
```

옵션 B — Adoptium Temurin 저장소:

```bash
# 키와 저장소 추가
sudo apt install -y wget apt-transport-https
wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public \
  | sudo gpg --dearmor -o /etc/apt/keyrings/adoptium.gpg
echo "deb [signed-by=/etc/apt/keyrings/adoptium.gpg] \
  https://packages.adoptium.net/artifactory/deb $(lsb_release -cs) main" \
  | sudo tee /etc/apt/sources.list.d/adoptium.list

sudo apt update
sudo apt install -y temurin-21-jdk
```

설치 경로:

```
/usr/lib/jvm/temurin-21-jdk-amd64
```

`~/.bashrc` (또는 `~/.zshrc`) 에:

```bash
export JAVA_HOME=/usr/lib/jvm/temurin-21-jdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"
```

#### 설치 확인 (공통)

```bash
java --version
# openjdk 21.x.x 또는 temurin 21.x.x

javac --version
# javac 21.x.x

echo $JAVA_HOME      # macOS / Linux
# /Library/Java/... 또는 /usr/lib/jvm/...
```

`java` / `javac` 가 모두 21 이면 준비 완료입니다.

### 3) `Hello.java` 파일 구조

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

- 파일명은 반드시 `public class` 이름과 같아야 합니다 → `Hello.java`
- `main(String[] args)` 가 **실행 진입점**입니다
- `System.out.println(...)` 은 한 줄을 출력하고 줄바꿈을 추가합니다

### 4) 컴파일과 실행

```bash
javac Hello.java   # Hello.class 생성
java Hello         # 실행 (확장자 없음!)
```

`javac` 는 `.java` 소스를 **바이트코드** `.class` 로 변환하고, `java` 는 JVM 위에서 그 바이트코드를 실행합니다.

### 5) IDE 선택

| 도구 | 장점 |
|---|---|
| IntelliJ IDEA Community | Java/Spring 작업에 가장 인기, 무료, 강력한 리팩터링 |
| VS Code + Extension Pack for Java | 가볍고 다목적, 멀티 언어 작업에 적합 |

본 강의는 두 도구 어느 쪽이든 동일하게 따라할 수 있게 작성되어 있습니다.

### 6) 운영체제별 IDE 설치

#### IntelliJ IDEA Community Edition

다운로드 페이지: <https://www.jetbrains.com/idea/download/?section=>

**Windows**

1. JetBrains 사이트의 **"Community Edition"** 탭에서 `.exe` 인스톨러 다운로드
2. 더블 클릭 후 마법사 따라가기 (64-bit launcher / Add bin folder to PATH 체크 추천)
3. 기본 설치 경로:
   ```
   C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2025.x\
   ```
4. 사용자 설정(프로젝트 캐시·인덱스):
   ```
   C:\Users\<유저>\AppData\Roaming\JetBrains\IdeaIC2025.x\
   ```

**macOS**

옵션 A — Homebrew:

```bash
brew install --cask intellij-idea-ce
```

옵션 B — JetBrains 사이트에서 `.dmg` 받아 드래그.
설치 경로:

```
/Applications/IntelliJ IDEA CE.app
```

사용자 설정:

```
~/Library/Application Support/JetBrains/IdeaIC2025.x/
```

**Linux (Ubuntu/Debian)**

옵션 A — Snap (가장 간편):

```bash
sudo snap install intellij-idea-community --classic
```

설치 경로: `/snap/intellij-idea-community/current/`

옵션 B — JetBrains 사이트의 `.tar.gz` 를 `/opt` 에 풀기:

```bash
sudo tar -xzf ideaIC-2025.x.tar.gz -C /opt
sudo ln -sf /opt/idea-IC-*/bin/idea.sh /usr/local/bin/idea
idea  # 실행
```

사용자 설정: `~/.config/JetBrains/IdeaIC2025.x/`

#### VS Code + Extension Pack for Java

다운로드 페이지: <https://code.visualstudio.com/Download>

**Windows**

1. `.exe` (User Installer 권장) 다운로드 후 설치
2. 기본 설치 경로(User Installer):
   ```
   C:\Users\<유저>\AppData\Local\Programs\Microsoft VS Code\
   ```
3. 시스템 전체 설치(System Installer) 의 경우:
   ```
   C:\Program Files\Microsoft VS Code\
   ```

**macOS**

옵션 A — Homebrew:

```bash
brew install --cask visual-studio-code
```

옵션 B — 사이트에서 `.zip` 받아 압축 해제 후 `Applications` 로 드래그.
설치 경로:

```
/Applications/Visual Studio Code.app
```

**Linux (Ubuntu/Debian)**

옵션 A — Microsoft `.deb`:

```bash
sudo apt install -y wget gpg apt-transport-https
wget -qO- https://packages.microsoft.com/keys/microsoft.asc \
  | gpg --dearmor | sudo tee /etc/apt/keyrings/packages.microsoft.gpg > /dev/null
echo "deb [arch=amd64 signed-by=/etc/apt/keyrings/packages.microsoft.gpg] \
  https://packages.microsoft.com/repos/code stable main" \
  | sudo tee /etc/apt/sources.list.d/vscode.list

sudo apt update
sudo apt install -y code
```

설치 경로: `/usr/share/code/` , 실행 파일은 `/usr/bin/code`.

옵션 B — Snap:

```bash
sudo snap install --classic code
```

**Java 확장 팩 설치 (공통)**

VS Code 를 실행하고 좌측 Extensions 아이콘(`Ctrl+Shift+X`) 에서
**`Extension Pack for Java`** (Microsoft 제공) 를 검색해 설치합니다.
명령줄로도 가능:

```bash
code --install-extension vscjava.vscode-java-pack
```

이 팩에 포함된 것:
- Language Support for Java (Red Hat)
- Debugger for Java
- Test Runner for Java
- Maven for Java
- Project Manager for Java

> **참고:** VS Code 의 Java 확장은 내부적으로 시스템 `JAVA_HOME` 을 그대로 사용합니다. 앞서 JDK 21 설치 후 `JAVA_HOME` 이 21 로 설정돼 있다면 별도 IDE 설정은 필요 없습니다.

## 예제로 보기

### 예제 1 — `Hello.java` : 가장 작은 Java 프로그램

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

**실행 결과**

```
Hello, Java!
```

**메모:** 파일명과 클래스명이 다르면 `class Hello is public, should be declared in a file named Hello.java` 컴파일 에러가 납니다.

### 예제 2 — `Args.java` : 명령줄 인자 받기

```java
public class Args {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("이름을 인자로 전달해 보세요.");
            return;
        }
        System.out.println("Hello, " + args[0] + "!");
    }
}
```

**실행 결과**

```
$ java Args Java
Hello, Java!
```

**메모:** `args` 는 `main` 의 매개변수입니다. `args.length` 로 갯수를 알 수 있고, `args[0]` 처럼 인덱스로 접근합니다.

### 예제 3 — `PrintFormats.java` : print / println / printf

```java
public class PrintFormats {
    public static void main(String[] args) {
        System.out.print("줄바꿈 ");
        System.out.print("없음\n");
        System.out.println("줄바꿈 자동");
        System.out.printf("이름=%s, 나이=%d%n", "지수", 21);
    }
}
```

**실행 결과**

```
줄바꿈 없음
줄바꿈 자동
이름=지수, 나이=21
```

**메모:** `printf` 의 `%n` 은 OS 에 맞는 줄바꿈을, `\n` 은 항상 LF 를 출력합니다.

### 예제 4 — `CommentsAndJavadoc.java` : 주석 세 가지

```java
/** 주석 종류를 보여 주는 데모. */
public class CommentsAndJavadoc {
    // 한 줄 주석
    /* 여러 줄
       주석 */
    public static void main(String[] args) {
        System.out.println(greeting("Java")); // 인라인 주석
    }

    /** 인사말을 반환. */
    static String greeting(String name) {
        return "Hello, " + name + "!";
    }
}
```

**실행 결과**

```
Hello, Java!
```

**메모:** `/** ... */` 형식의 **Javadoc** 주석은 `javadoc` 도구가 HTML 문서로 변환할 수 있는 공식 API 주석입니다.

## 자주 하는 실수

1. 파일명을 `hello.java`(소문자) 로 저장해 클래스명과 어긋남
2. `java Hello.class` 처럼 확장자까지 입력 → `java Hello` 가 올바름
3. `main` 의 시그니처를 `void main()` 으로 바꿔 진입점을 잃음
4. `System.out.println` 의 `out` 을 빼먹고 `System.println` 으로 작성
5. 한글 출력이 깨질 때 인코딩 확인: 파일을 UTF-8 로 저장, 콘솔도 UTF-8 (`chcp 65001` 윈도우)

## 정리

- Java 프로그램은 `public class Xxx` 안의 `public static void main(String[] args)` 에서 시작합니다
- `.java` 를 `javac` 으로 컴파일하면 `.class` 가 생기고, `java` 로 실행합니다
- `System.out` 의 `print` / `println` / `printf` 를 상황에 맞게 사용합니다

## 직접 해 보기

```bash
cd 01_기초/01_Java_시작하기/src
javac Hello.java
java Hello
```

다른 예제도 같은 방식으로:

```bash
javac Args.java
java Args 코딩나우
```

## 다음 단원

[02_변수와_타입](../02_변수와_타입/) — primitive 8종, 형변환, `var` 키워드를 배웁니다.
