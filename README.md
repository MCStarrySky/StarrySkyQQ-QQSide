# Introduction

毕竟 MC 端的数据库跟这边是一样的，于是我就懒得造轮子，直接把代码cv过来了。<br>
现在是 TabooLib 和 Mirai 融合了一下，TabooLib 的工具真是太好用啦（

# Building

* [Gradle](https://gradle.org/) - Dependency Management

The GradleWrapper in included in this project.

**Windows:**

```
gradlew.bat clean build
```

**macOS/Linux:**

```
./gradlew clean build
```

Build artifacts should be found in `./build/libs` folder.