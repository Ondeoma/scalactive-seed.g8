// npm run dev: Viteの開発サーバーと、sbt ~fastLinkJS(Scalaのソースの変更を見張ってJSを作り直す)を一緒に起動します.
// Scalaや.stを保存すると、sbtがJSを作り直し、Viteがブラウザを再読み込みします.
//
// Viteのプラグインも起動時にsbtでJSを作るため、2つのsbtが同時に動かないように、
// Viteの起動が終わってからsbt ~fastLinkJSを始めます.
// npm run dev の後ろに書いた引数は、Viteに渡します(例: npm run dev -- --port 3000).

import { spawn } from "node:child_process";

const isWindows = process.platform === "win32";
const children = [];

function run(command, args, options) {
  const child = spawn(command, args, { shell: isWindows, ...options });
  children.push(child);
  child.on("exit", (code) => stopAll(code ?? 0));
  return child;
}

let stopping = false;

function stopAll(code) {
  if (stopping) return;
  stopping = true;
  children.forEach((c) => c.exitCode === null && c.kill());
  process.exit(code);
}

process.on("SIGINT", () => stopAll(0));
process.on("SIGTERM", () => stopAll(0));

let sbtStarted = false;

function startSbt() {
  sbtStarted = true;
  console.log("\n[dev] sbt ~fastLinkJS を起動します(Scalaの変更を見張ります)\n");
  // 標準入力を閉じるとsbtの見張りが止まるため、つないだままにする
  run("sbt", ["-Dsbt.supershell=false", "~fastLinkJS"], { stdio: ["pipe", "inherit", "inherit"] });
}

const vite = run("npx", ["vite", ...process.argv.slice(2)], { stdio: ["inherit", "pipe", "inherit"] });

vite.stdout.on("data", (chunk) => {
  process.stdout.write(chunk);
  // 「ready in ...」が出たら、Viteの起動(とプラグインによる最初のsbtの実行)が終わっている
  if (!sbtStarted && chunk.toString().includes("ready in")) startSbt();
});
