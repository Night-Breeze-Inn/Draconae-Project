# Contributing to Draconae Project

First off, thank you for considering contributing to the Draconae Project! We welcome any help, whether it's reporting a bug, proposing a new feature, improving documentation, or writing code.

## 📜 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
    - [Reporting Bugs](#reporting-bugs)
    - [Suggesting Enhancements](#suggesting-enhancements)
    - [Your First Code Contribution](#your-first-code-contribution)
    - [Pull Requests](#pull-requests)
- [Development Setup](#development-setup)
- [Style Guides](#style-guides)
    - [Git Commit Messages](#git-commit-messages)
    - [Java Style Guide](#java-style-guide)
    - [FXML Style Guide](#fxml-style-guide)
    - [Conventional Commits And Warnings](#conventional-commits-and-warnings)

## Code of Conduct

This project and everyone participating in it is governed by a Code of Conduct. While we don't have a formal one written yet, we expect all contributors to be respectful, open, and considerate. Harassment or exclusionary behavior will not be tolerated. (We may adopt a formal Code of Conduct like the [Contributor Covenant](https://www.contributor-covenant.org/) in the future).

## How Can I Contribute?

### Reporting Bugs

If you find a bug, please ensure the bug was not already reported by searching on GitHub under [Issues](https://github.com/KoRIOz675/Draconae-Project/issues).

If you're unable to find an open issue addressing the problem, [open a new one](https://github.com/KoRIOz675/Draconae-Project/issues/new). Be sure to include:

- A **clear and descriptive title**.
- A **detailed description of the issue**, including steps to reproduce the bug.
- **Expected behavior** vs. **actual behavior**.
- **Screenshots or animated GIFs** if they help illustrate the problem.
- **Your environment details** (OS, Java version, etc.).

### Suggesting Enhancements

If you have an idea for a new feature or an improvement to an existing one:

1. Search the [Issues](https://github.com/KoRIOz675/Draconae-Project/issues) to see if the enhancement has already been suggested.
2. If not, [open a new issue](https://github.com/KoRIOz675/Draconae-Project/issues/new) to start a discussion.
    - Provide a clear and descriptive title.
    - Explain the enhancement and why it would be beneficial.
    - Include any relevant mockups, examples, or details.

### Your First Code Contribution

Unsure where to begin contributing to Draconae? You can start by looking through `good first issue` or `help wanted` issues:

- [Good first issues](https://github.com/KoRIOz675/Draconae-Project/labels/good%20first%20issue) - issues which should only require a few lines of code, and a test or two.
- [Help wanted issues](https://github.com/KoRIOz675/Draconae-Project/labels/help%20wanted) - issues which should be a bit more involved than `good first issue` issues.

### Pull Requests

1.  **Fork the repository** on GitHub.
2.  **Clone your fork** locally: `git clone https://github.com/YOUR_USERNAME/Draconae-Project.git`
3.  **Create a new branch** for your changes: `git checkout -b feature/your-feature-name` or `fix/bug-fix-description`.
4.  **Make your changes.** Ensure you follow the [Style Guides](#style-guides).
5.  **Test your changes** thoroughly.
6.  **Commit your changes** with a clear and descriptive commit message. See [Git Commit Messages](#git-commit-messages).
    ```bash
    git add .
    git commit -m "feat: Add some amazing feature"
    ```
7.  **Push your branch** to your fork: `git push origin feature/your-feature-name`.
8.  **Open a Pull Request (PR)** to the `main` branch of the `KoRIOz675/Draconae-Project` repository.
    - Provide a clear title and description for your PR, linking to any relevant issues (e.g., "Closes #123").
    - Be prepared to discuss your changes and make adjustments if requested by the maintainers.

## Development Setup

Refer to the [Getting Started](#-getting-started) section in the `README.md` for instructions on how to clone the repository, install prerequisites, and build/run the project.

Key tools:

- Java 11+
- Maven
- An IDE like IntelliJ IDEA, Eclipse, or VS Code with Java extensions is recommended.

## Style Guides

### Git Commit Messages

- Use descriptive warning (e.g., `MINOR:`, `MEDIUM:`, `MAJOR:`, `CRITICAL:` )
- use conventional commits (e.g., `fix:`, `docs:`, `style:`)
- Use the past tense ("Added feature" not "Add feature").
- Use the imperative mood ("Move cursor to..." not "Moves cursor to...").
- Limit the first line to 72 characters or less.
- Reference issues and PRs liberally after the first line.

### Java Style Guide

- This project uses [Prettier](https://prettier.io/) with `prettier-plugin-java` for code formatting. The configuration is in `.prettierrc.yaml` and `pom.xml` includes a Maven plugin for Prettier.
- Please ensure your code is formatted by Prettier before committing. You can run `mvn prettier:write` to format the code.
- Follow standard Java naming conventions (e.g., `camelCase` for methods and variables, `PascalCase` for classes).
- Write clear and concise comments where necessary.

### FXML Style Guide

- Keep FXML files clean and readable.
- Use meaningful `fx:id` values.
- Organize UI elements logically.

### Conventional Commits And Warnings

Conventional Commits

```markdown
**fix**         *a bug fix has occurred*
**chore**       *changes that do not relate to a fix or feature and don't modify src or 
                test files (for example updating dependencies)*
**refactor**    *refactored code that neither fixes a bug nor adds a feature*
**docs**        *updates to documentation such as a the README or other markdown files*
**style**       *changes that do not affect the meaning of the code, likely related to code 
                formatting such as white-space, missing colons, and so on.*
**test**        *including new or correcting previous tests*
**perf**        *performance improvements*
**ci**          *continuous integration related*
**build**       *changes that affect the build system or external dependencies*
**revert**      *reverts a previous commit*
```

descriptive warning

```markdown
**MINOR**       _minor change, very low risk of impact. It is often the case for
                code additions that don't touch live code. As a rule of thumb, a
                patch tagged "MINOR" is safe enough to be backported to stable
                branches. For a bug, it generally indicates an annoyance, nothing
                more._

**MEDIUM**      _medium risk, may cause unexpected regressions of low importance or
                which may quickly be discovered. In short, the patch is safe but
                touches working areas and it is always possible that you missed
                something you didn't know existed (eg: adding a "case" entry or
                an error message after adding an error code to an enum). For a bug,
                it generally indicates something odd which requires changing the
                configuration in an undesired way to work around the issue._

**MAJOR**       _major risk of hidden regression. This happens when large parts of
                the code are rearranged, when new timeouts are introduced, when
                sensitive parts of the session scheduling are touched, etc... We
                should only exceptionally find such patches in stable branches when
                there is no other option to fix a design issue. For a bug, it
                indicates severe reliability issues for which workarounds are
                identified with or without performance impacts._

**CRITICAL**    _medium-term reliability or security is at risk and workarounds,
                if they exist, might not always be acceptable. An upgrade is
                absolutely required. A maintenance release may be emitted even if
                only one of these bugs are fixed. Note that this tag is only used
                with bugs. Such patches must indicate what is the first version
                affected, and if known, the commit ID which introduced the issue._
```

---

Thank you for contributing to the Draconae Project!
