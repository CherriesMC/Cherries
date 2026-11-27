Cherries
===========

[![Cherries CI](https://github.com/CherriesMC/Cherries/actions/workflows/build.yml/badge.svg?branch=cherries%2F1.21.8)](https://github.com/CherriesMC/Cherries/actions/workflows/build.yml)
[![Cherries Download](https://img.shields.io/github/downloads/CherriesMC/Cherries/total?color=0&logo=github)](https://github.com/CherriesMC/Cherries/releases)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE.md)

[English](README.md) | **中文**

> Cherries 是 [Leaves](https://github.com/LeavesMC/Leaves) 的硬分叉，Leaves 又是 [Paper](https://github.com/PaperMC/Paper) 的分叉。
>
> CherriesMC 由 EndlessMC 服务器团队管理维护。

## 这个分叉做了什么

- 保持 Leaves、Paper、Bukkit 插件 API 可用。`io.papermc.paper.*` 和 `org.leavesmc.leaves.*` 包名刻意不改。
- 保持 `leaves.yml` 及其键不变，现有服务器可以直接复用。
- Cherries 专属功能与选项放在独立的 `cherries.yml` 中。
- 选择性回移上游修复，并携带 Cherries 自己的优化。

当前维护 1.21.8 线。上游 Leaves 已不再维护 1.21.8 分支，因此新版本上游的修复采用按需 backport，而不是整包合并。

## 对于服务器管理员

Cherries 使用与 Paper、Leaves 相同的 leavesclip（paperclip 分叉）分发方式。

请从 [Releases](https://github.com/CherriesMC/Cherries/releases) 下载最新构建。

也可以通过 [自行构建](#自行构建) 生成。

## 对于插件开发者

运行时 API 与 Leaves 源码兼容，Java 包名没有改动，因此现有的 Leaves、Paper、Bukkit 插件无需重新编译即可继续使用。

Cherries 的 Maven 发布尚未上线。上线前请自行构建服务端/API，并使用生成的 `leaves-api` jar。

## 自行构建

需要 JDK 21，以及可以正常访问 GitHub/Maven 仓库的网络。

```bash
./gradlew applyAllPatches
./gradlew createMojmapLeavesclipJar
```

产物位于 `leaves-server/build/libs`。Gradle 模块名仍保留 `leaves-*` 前缀（为了减少上游同步冲突），CI 会把发布产物重命名为 `cherries-*`。

## 兼容性

- Bukkit 插件：支持。
- Paper 插件：支持。
- Leaves 插件（`leaves-plugin.json`，包括 Mixin 插件）：支持。
- `leaves.yml`：保持不变，现有文件可直接使用。
- `cherries.yml`：Cherries 专属选项，逐步加入。

## 许可证

Cherries 使用 GNU General Public License v3.0，与 Leaves、Paper 相同。上游版权声明保留，详见 [LICENSE.md](LICENSE.md)。Cherries 的修改同样以该许可证开放源代码。

## 贡献

参见 [贡献须知](docs/CONTRIBUTING_cn.md)。

## 特别感谢

[<img src="https://user-images.githubusercontent.com/21148213/121807008-8ffc6700-cc52-11eb-96a7-2f6f260f8fda.png" alt="" width="150">](https://www.jetbrains.com)

[JetBrains](https://www.jetbrains.com/)，IntelliJ IDEA 的创造者，通过 [开源许可证](https://www.jetbrains.com/opensource/) 支持开源项目。
