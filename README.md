# Cherries

[简体中文](#简体中文) | [English (US)](#english-us)

> **AI 生成声明**：本项目包含 AI 辅助生成或修改的内容，代码仅供正常使用、参考和学习，**请不要将其再次用于 AI 训练**！
> **AI Generated Disclaimer**: This project contains AI-assisted or AI-modified content. The code is provided for normal use, reference, and learning only. **Please do not use it for AI training again!**

[![License](https://img.shields.io/badge/license-GPLv3-blue.svg)](LICENSE.md)
[![Version](https://img.shields.io/badge/version-1.21.8-informational.svg)](https://github.com/CherriesMC/Cherries/releases)
[![Build](https://github.com/CherriesMC/Cherries/actions/workflows/test.yml/badge.svg)](https://github.com/CherriesMC/Cherries/actions/workflows/test.yml)

---

## 简体中文

### 简介

Cherries 是一个基于 [Leaves](https://github.com/LeavesMC/Leaves) 的 Minecraft 服务端分支，而 Leaves 本身是 [Paper](https://github.com/PaperMC/Paper) 的分支。Cherries 延续“修复原版被破坏特性”的目标，在保持插件兼容性的同时提供假人、协议兼容、性能优化等实用能力。

项目地址：<https://github.com/CherriesMC/Cherries/>

### 功能特性

- **原版特性修复**：通过 Minecraft、Paper 服务端与 API 三层补丁，修复与当前原版行为不一致的问题。
- **假人支持**：创建、移除、保存与加载假人，并为假人配置动作与行为。
- **协议兼容**：支持 Carpet、Servux、Syncmatica、REI、Bladeren 等常用协议。
- **性能优化**：内置 Lithium 相关优化补丁，减少实体、方块实体与漏斗等热点开销。
- **配置系统**：通过 `leaves.yml` 与 `/leaves config` 管理服务端行为。
- **插件兼容**：兼容 Bukkit、Spigot、Paper 插件生态。

### 环境要求

- Minecraft 版本：1.21.8。
- Java：21 或更高版本。
- 构建工具：Gradle（仅构建时需要）。
- 网络：首次构建需要访问 GitHub 与 PaperMC Maven 仓库。

### 安装

- 将构建或下载得到的服务端 jar 放入任意目录。
- 执行 `java -jar cherries-1.21.8.jar` 启动服务器。
- 首次启动会在服务端根目录生成 `leaves.yml` 与相关配置文件。

### 配置（`leaves.yml`）

- 配置文件位于服务端根目录。
- 可在游戏内或控制台使用 `/leaves config` 查看与修改配置项。
- 修改后可使用 `/leaves reload` 重载配置。
- 原版与 Paper 配置仍位于 `server.properties`、`bukkit.yml`、`spigot.yml`、`paper-global.yml` 等文件。

### 命令

- `/leaves config`：查看或修改 Cherries 配置。
- `/leaves reload`：重载配置。
- `/leaves report <bug-report|not-vanilla>`：生成预填充的问题报告链接。
- `/leaves blockupdate`：切换方块更新相关行为。
- `/leaves counter`：查看漏斗计数器信息。
- `/bot ...`：假人管理命令，包含创建、移除、列表、保存、加载、动作与配置等子命令。

### 权限

- `bukkit.command.leaves`：使用 `/leaves` 命令的基础权限。
- `bukkit.command.leaves.config`：修改配置。
- `bukkit.command.leaves.reload`：重载配置。
- `bukkit.command.leaves.report`：生成问题报告链接。
- `bukkit.command.bot`：使用 `/bot` 假人命令。

### 构建

本项目使用与 Paper 相同的补丁工作流，构建依赖 **Gradle** 与 `leavesweight` 插件完成 Minecraft 反编译、补丁应用与重编译，**无法使用 Maven 构建**。

```bash
./gradlew applyAllPatches
./gradlew createMojmapLeavesclipJar
```

构建产物位于 `leaves-server/build/libs/`，文件名为 `leaves-leavesclip-<版本>-mojmap.jar`。

### 兼容性

- 目标版本：Minecraft 1.21.8。
- 服务端类型：可作为 Paper 系服务端直接替换使用。
- 插件：兼容 Bukkit、Spigot、Paper 插件。
- 上游：与 Leaves、Paper 的补丁体系保持同步。

### 变更日志

- `1.21.8`：项目品牌迁移为 Cherries，作者变更为 OutHimic 与 EndlessMC Organization；项目地址迁移至 <https://github.com/CherriesMC/Cherries/>；移除指向上游 LeavesMC API 的自动更新与版本检查逻辑；清理上游发布脚本、发布流水线与非必要文件；补全 `.gitignore`；`README.md` 统一为简体中文与美式英语双语格式。
- `1.21.8`（上游基线）：基于 Leaves 1.21.8 与 Paper 的补丁体系。

### 许可证

本项目使用 GNU General Public License v3 许可证，继承自 Leaves、Paper、Spigot、Bukkit 与 CraftBukkit；部分贡献者代码以 MIT 许可证发布，详见 [LICENSE.md](LICENSE.md)。

Copyright (c) 2026 OutHimic and EndlessMC Organization

---

## English (US)

### Overview

Cherries is a Minecraft server fork based on [Leaves](https://github.com/LeavesMC/Leaves), which is itself a fork of [Paper](https://github.com/PaperMC/Paper). Cherries continues the goal of repairing broken vanilla behavior while keeping plugin compatibility and providing fake players, protocol compatibility, and performance improvements.

Project URL: <https://github.com/CherriesMC/Cherries/>

### Features

- **Vanilla behavior fixes**: Minecraft, Paper server, and API patches that restore behavior consistent with the current vanilla version.
- **Fake player support**: create, remove, save, and load fake players, including configurable actions and behavior.
- **Protocol compatibility**: support for Carpet, Servux, Syncmatica, REI, Bladeren, and related protocols.
- **Performance improvements**: Lithium-based patches that reduce hot-path costs for entities, block entities, hoppers, and more.
- **Configuration system**: manage server behavior through `leaves.yml` and `/leaves config`.
- **Plugin compatibility**: compatible with the Bukkit, Spigot, and Paper plugin ecosystem.

### Requirements

- Minecraft version: 1.21.8.
- Java: 21 or newer.
- Build tool: Gradle (build only).
- Network: the first build needs access to GitHub and the PaperMC Maven repository.

### Installation

- Place the built or downloaded server jar in any directory.
- Start the server with `java -jar cherries-1.21.8.jar`.
- On first startup, `leaves.yml` and related configuration files are generated in the server root directory.

### Configuration (`leaves.yml`)

- The configuration file is located in the server root directory.
- Use `/leaves config` in game or from the console to view and modify options.
- Run `/leaves reload` after changing the configuration.
- Vanilla and Paper settings remain in `server.properties`, `bukkit.yml`, `spigot.yml`, `paper-global.yml`, and related files.

### Commands

- `/leaves config`: view or modify Cherries configuration.
- `/leaves reload`: reload the configuration.
- `/leaves report <bug-report|not-vanilla>`: generate a pre-filled issue report link.
- `/leaves blockupdate`: toggle block update related behavior.
- `/leaves counter`: show hopper counter information.
- `/bot ...`: fake player management, including create, remove, list, save, load, action, and config subcommands.

### Permissions

- `bukkit.command.leaves`: base permission for `/leaves`.
- `bukkit.command.leaves.config`: modify configuration.
- `bukkit.command.leaves.reload`: reload configuration.
- `bukkit.command.leaves.report`: generate issue report links.
- `bukkit.command.bot`: use the `/bot` fake player command.

### Building

This project uses the same patch workflow as Paper. Building depends on **Gradle** and the `leavesweight` plugin to decompile Minecraft, apply patches, and recompile the server. **Maven cannot be used to build this project.**

```bash
./gradlew applyAllPatches
./gradlew createMojmapLeavesclipJar
```

The output is located in `leaves-server/build/libs/` and is named `leaves-leavesclip-<version>-mojmap.jar`.

### Compatibility

- Target version: Minecraft 1.21.8.
- Server type: a drop-in replacement for Paper-family servers.
- Plugins: compatible with Bukkit, Spigot, and Paper plugins.
- Upstream: kept in sync with the Leaves and Paper patch systems.

### Changelog

- `1.21.8`: rebranded the project as Cherries and changed the authors to OutHimic and EndlessMC Organization; moved the project to <https://github.com/CherriesMC/Cherries/>; removed auto-update and version-check logic that pointed to the upstream LeavesMC API; cleaned up upstream release scripts, release workflows, and unnecessary files; completed `.gitignore`; unified `README.md` as a bilingual Simplified Chinese and English (US) document.
- `1.21.8` (upstream baseline): based on Leaves 1.21.8 and the Paper patch system.

### License

This project is licensed under the GNU General Public License v3, inherited from Leaves, Paper, Spigot, Bukkit, and CraftBukkit. Some contributor code is released under the MIT License. See [LICENSE.md](LICENSE.md) for details.

Copyright (c) 2026 OutHimic and EndlessMC Organization

---

## 致谢 / Thanks

- [PaperMC](https://github.com/PaperMC) 与 [LeavesMC](https://github.com/LeavesMC) 及其贡献者 / and their contributors
- [JetBrains](https://www.jetbrains.com/) 提供的开源许可证 / for the Open Source Licenses
- 所有参与测试与反馈的玩家和服主 / all players and server owners who tested and reported issues