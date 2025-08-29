import { defineStore } from "pinia";
import { ref } from "vue";
import { getUserInfo } from "@/api/user";

export const userLoginStore = defineStore("loginUser", () => {
  const loginUser = ref<any>({
    username: "未登录",
  });

  // 获取用户信息
  async function fetchLoginUser() {
    const res = await getUserInfo();
    if (res.data.code === 0 && res.data.data) {
      loginUser.value = res.data.data;
    } /*else {
      // TODO: 测试代码，模拟登录超时. 实际项目中删除
      setTimeout(() => {
        loginUser.value = { username: "还未登录", id: 1 };
      }, 3000);
    }
      */
  }

  // 在某种情况下，直接获取到用户信息。手动设置用户信息
  function setLoginUser(newLoginUser: any) {
    loginUser.value = newLoginUser;
  }

  return { loginUser, fetchLoginUser, setLoginUser };
});
