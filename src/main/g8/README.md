# README

cf. https://www.scala-js.org/doc/tutorial/scalajs-vite.html#introducing-scalajs

## Dependencies

* [sbt](https://www.scala-sbt.org/)
* [node.js](https://nodejs.org/) 24~

## Project structure

```
src/main/
  scalactive/          ... .st(単一ファイルコンポーネント) / single file components
    components/
      MainComponent.st ... object components.MainComponent
      SubComponent.st
  scala/
    App.scala          ... main
```

コンポーネントは `.st` ファイルに、`<script lang="scala">`・`<template>`・`<style scoped>` の3つのブロックで書きます。
sbtプラグイン(sbt-scalactive)が、コンパイル時に `.st` からScalaのソースを生成します。
ディレクトリがパッケージに、ファイル名がobject名になります。

Components are written in `.st` files with three blocks: `<script lang="scala">`, `<template>` and `<style scoped>`.
The sbt plugin (sbt-scalactive) generates Scala sources from them on compile.
The directory becomes the package and the file name becomes the object name.

cf. https://ondeoma.github.io/scalactive/

## Run

初回起動時は以下を実行しnpmパッケージを取得してください。

On first startup, run the following command to install npm packages.

```
npm install
```

以下で開発サーバーを起動します。

Run the following command to start the development server.

```
npm run dev
```

Viteの開発サーバーと `sbt ~fastLinkJS` を一緒に起動します(`dev.mjs`)。
Scalaや `.st` のファイルを保存すると、JSが作り直され、ブラウザが自動で再読み込みされます。
止めるときは Ctrl+C を押します(両方とも止まります)。

This starts the Vite dev server together with `sbt ~fastLinkJS` (see `dev.mjs`).
When you save a Scala or `.st` file, the JS is rebuilt and the browser reloads automatically.
Press Ctrl+C to stop both.

※時折記述が正しくてもコンパイルエラーが出るような気がします。  
不調な時は一度停止して再起動等をお試しください。

\* Occasionally, compile errors may occur even when the code is written correctly.  
If things seem unstable, please try stopping and restarting the process.

Viteだけを起動したい場合(sbtを別のターミナルで動かす場合など)は `npm run dev:vite` を使います。

To start only Vite (for example, when you run sbt in another terminal), use `npm run dev:vite`.

## Build

以下のコマンドを実行します。

Run the following command.

```
npm run build
```

※以下のコマンドでビルドしたソースをローカルで確認できます。

You can check the locally built source using the following command.

```
npm run preview
```

## Editor support

VS CodeとIntelliJ IDEAで、`.st` のハイライト・補完・型エラーの表示などが使えます。
エディタの拡張は、一度 `sbt compile` を実行した後(`target/scalactive-lsp.json` が作られた後)に動作します。

Syntax highlighting, completion, type errors and more are available for `.st` in VS Code and IntelliJ IDEA.
The editor extensions work after running `sbt compile` once (which writes `target/scalactive-lsp.json`).

cf. https://github.com/Ondeoma/scalactive/tree/main/editors
