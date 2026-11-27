# Bundled plugins

## AcceleratedRecoiling

- Upstream: <https://github.com/wiyuka-owo/AcceleratedRecoiling>
- Cherries fork: <https://github.com/CherriesMC/AcceleratedRecoiling>
- Release: `21.8.22-cherries.1`
- SHA256: `35387A6CFDFA25B3A2228781C351E0A194A2574699FC2BA8165B0D2C5A44E837`
- License: MIT, see `AcceleratedRecoiling-LICENSE.txt`

The jar is extracted to `plugins/AcceleratedRecoiling.jar` before Paper scans the
plugins directory, but only when `cherries.yml` enables it:

```yaml
optimizations:
  accelerated-recoiling:
    enabled: true
    enable-entity-collision: true
    full-bounding-box-ladders: false
```

`enabled: false` removes the Cherries-managed jar again. A user-managed
`plugins/AcceleratedRecoiling.jar` is never overwritten or removed.
