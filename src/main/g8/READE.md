# README

cf. https://www.scala-js.org/doc/tutorial/scalajs-vite.html#introducing-scalajs

## Dependencies

* [sbt](https://www.scala-sbt.org/)
* [node.js](https://nodejs.org/) 24~

## Run

初回起動時は以下を実行しnpmパッケージを取得してください。

On first startup, run the following command to install npm packages.

```
npm install
```

以下を2つのターミナルでそれぞれ起動します。

Run the following commands in two separate terminals.

```
sbt ~fastLinkJS
```

※時折記述が正しくてもコンパイルエラーが出るような気がします。  
不調な時は一度停止して再起動等をお試しください。

\* Occasionally, compile errors may occur even when the code is written correctly.  
If things seem unstable, please try stopping and restarting the process.

```
npm run dev
```

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

