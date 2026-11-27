Cherries
===========

[![Cherries CI](https://github.com/CherriesMC/Cherries/actions/workflows/build.yml/badge.svg?branch=cherries%2F1.21.8)](https://github.com/CherriesMC/Cherries/actions/workflows/build.yml)
[![Cherries Download](https://img.shields.io/github/downloads/CherriesMC/Cherries/total?color=0&logo=github)](https://github.com/CherriesMC/Cherries/releases)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE.md)

**English** | [中文](README_cn.md)

> Cherries is a hard fork of [Leaves](https://github.com/LeavesMC/Leaves), which is a fork of [Paper](https://github.com/PaperMC/Paper).
>
> CherriesMC is maintained by the EndlessMC server team.

## What this fork changes

- Keeps the Leaves, Paper and Bukkit plugin APIs working. Package names such as
  `io.papermc.paper.*` and `org.leavesmc.leaves.*` are intentionally unchanged.
- Keeps `leaves.yml` and its keys unchanged for existing servers.
- Adds Cherries-only features and options in a separate `cherries.yml`.
- Backports selected fixes and carries Cherries-specific optimizations.

The 1.21.8 line is the current target. Upstream Leaves no longer maintains a
1.21.8 branch, so fixes from newer upstream releases are backported selectively
instead of merged wholesale.

## How To (Server Admins)

Cherries uses the same leavesclip (paperclip fork) distribution system as Paper
and Leaves.

Download the latest build from the [releases page](https://github.com/CherriesMC/Cherries/releases).

You can also [build it yourself](#building).

## How To (Plugin Developers)

The runtime API is source-compatible with Leaves. The Java packages are not
renamed, so existing Leaves, Paper and Bukkit plugins keep working without
recompilation.

Maven publishing for Cherries is not available yet. Until it is, build the
server/API locally and use the produced `leaves-api` jar.

## Building

You need JDK 21 and working access to GitHub/Maven repositories.

```bash
./gradlew applyAllPatches
./gradlew createMojmapLeavesclipJar
```

The jars are written to `leaves-server/build/libs`. The Gradle module names
still use the `leaves-*` prefix on purpose; release artifacts are renamed to
`cherries-*` by CI.

## Compatibility

- Bukkit plugins: supported.
- Paper plugins: supported.
- Leaves plugins (`leaves-plugin.json`, including Mixin-based plugins): supported.
- `leaves.yml`: unchanged; existing files can be reused.
- `cherries.yml`: Cherries-only options, added incrementally.

## License

Cherries is licensed under the GNU General Public License v3.0, the same
license as Leaves and Paper. Upstream copyright notices are retained. See
[LICENSE.md](LICENSE.md). Cherries modifications are source-available under the
same license.

## Pull Requests

See [Contributing](docs/CONTRIBUTING.md).

## Special Thanks To:

[<img src="https://user-images.githubusercontent.com/21148213/121807008-8ffc6700-cc52-11eb-96a7-2f6f260f8fda.png" alt="" width="150">](https://www.jetbrains.com)

[JetBrains](https://www.jetbrains.com/), creators of IntelliJ IDEA, supports
open-source projects with their [Open Source Licenses](https://www.jetbrains.com/opensource/).
