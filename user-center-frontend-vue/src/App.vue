<template>
  <div id="app">
    <BasicLayout />
  </div>
</template>

<style></style>

<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { userLoginStore } from "@/stores/UserLoginStore";

const loginUserStore = userLoginStore();
loginUserStore.fetchLoginUser();
</script>
