<script lang="scala">
case class Props(name: RV[String],
                 onEnter: Unit => Unit = _ => ())
</script>

<template>
  <div class="sub">
    Sub Component: <input type="text" class="input" v-model="props.name" @keydown.enter="props.onEnter(())">
  </div>
</template>

<style scoped>
.sub {
  background-color: mediumpurple;
  padding: 5px;
}
</style>
