<script lang="scala">
case class Human(name: String)

case class State(name: RV[String] = RV(""),
                 humans: RVList[Human] = RVList(Nil),
                 html: RV[String] = RV("""<span style="color: red;">HTML</span>"""))

def initState() = State()

def addHuman(s: State): Unit = {
  if (s.name.v.trim.nonEmpty) {
    s.humans.add(Human(s.name.v.trim))
    s.name := ""
  }
}
</script>

<template>
  <div class="main">
    <h1>Humans</h1>
    <table v-show="s.humans.mapC(_.nonEmpty)">
      <thead>
        <tr>
          <th>No.</th>
          <th>Name</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(human, i) in s.humans">
          <td>{{ i + 1 }}</td>
          <td>{{ human.mapC(_.name) }}</td>
        </tr>
      </tbody>
    </table>
    <div v-show="s.humans.mapC(_.isEmpty)">Empty...</div>

    <hr>

    <div>Input Human Name: {{ s.name }}</div>
    <SubComponent :name="s.name" @enter="addHuman(s)" />
    <button @click="addHuman(s)">Add Human</button>

    <hr>

    <h1>Rendering HTML</h1>
    <input type="text" class="input" v-model="s.html">
    <div v-html="s.html"></div>

    <hr>

    <div>
      Powered By <img src="scala-js-site-logo.svg" height="22" alt="Scala.js" class="logo">
    </div>
  </div>
</template>

<style scoped>
.main {
  background-color: deepskyblue;
  padding: 5px;
}

.logo {
  position: relative;
  top: 5px;
}
</style>
