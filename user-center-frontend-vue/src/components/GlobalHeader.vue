<template>
  <div id="globalHeader">
    <a-row :wrap="false">
      <a-col flex="300px">
        <div class="title-bar">
          <img class="logo" src="@/assets/logo.png" alt="Logo" />
          <div class="title">用户中心</div>
        </div>
      </a-col>
      <a-col flex="auto">
        <a-menu
          v-model:selectedKeys="current"
          mode="horizontal"
          :items="items"
          @click="doMenuClick"
        />
      </a-col>
      <a-col flex="80px">
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            欢迎，{{ loginUserStore.loginUser.username ?? "无名" }}
          </div>
          <div v-else>
            <a-button type="primary" @click="$router.push('/user/login')">
              登录
            </a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script lang="ts" setup>
import { h, ref } from "vue";
import { CrownOutlined, HomeOutlined } from "@ant-design/icons-vue";
import { MenuProps } from "ant-design-vue";
import { useRouter } from "vue-router";
import { userLoginStore } from "@/stores/UserLoginStore";
const loginUserStore = userLoginStore();

const router = useRouter();
const doMenuClick = ({ key }: { key: string }) => {
  router.push({
    path: key,
  });
};
const current = ref<string[]>(["mail"]);
router.afterEach((to) => {
  current.value = [to.path];
});
const items = ref<MenuProps["items"]>([
  {
    key: "/",
    icon: () => h(HomeOutlined),
    label: "主页",
    title: "主页",
  },
  {
    key: "/user/login",
    label: "用户登录",
    title: "用户登录",
  },
  {
    key: "/user/register",
    label: "用户注册",
    title: "用户注册",
  },
  {
    key: "/admin/userManage",
    icon: () => h(CrownOutlined),
    label: "用户管理",
    title: "用户管理",
  },
  {
    key: "others",
    label: h(
      "a",
      { href: "https://www.ytechtrade.top/", target: "_blank" },
      "游戏导航"
    ),
    title: "游戏导航",
  },
]);
</script>
<style scoped>
.title-bar {
  display: flex;
  align-items: center;
}

.title {
  font-size: 18px;
  color: #333;
  margin-left: 16px;
}

.logo {
  height: 48px;
}
</style>
