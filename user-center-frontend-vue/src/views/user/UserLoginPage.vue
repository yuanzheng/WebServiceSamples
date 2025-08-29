<template>
  <div id="userLoginPage">
    <h2 class="title">User Login</h2>
    <a-form
      style="max-width: 400px; margin: 0 auto"
      :model="formState"
      name="basic"
      label-align="left"
      :label-col="{ span: 6 }"
      :wrapper-col="{ span: 20 }"
      autocomplete="off"
      @finish="handleSubmit"
    >
      <a-form-item
        label="Username"
        name="userAccount"
        :rules="[{ required: true, message: 'Please input your username!' }]"
      >
        <a-input
          v-model:value="formState.userAccount"
          placeholder="input username"
        />
      </a-form-item>

      <a-form-item
        label="Password"
        name="userPassword"
        :rules="[
          { required: true, message: 'Please input your password!' },
          { min: 8, message: 'Password must be minimum 8 characters.' },
        ]"
      >
        <a-input-password
          v-model:value="formState.userPassword"
          placeholder="input password"
        />
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 6, span: 20 }">
        <a-button type="primary" html-type="submit">Submit</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script lang="ts" setup>
import { userLogin } from "@/api/user";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import { userLoginStore } from "@/stores/UserLoginStore";
import { message } from "ant-design-vue";

interface FormState {
  userAccount: string;
  userPassword: string;
}

const formState = reactive<FormState>({
  userAccount: "",
  userPassword: "",
});

const router = useRouter();
const loginUserStore = userLoginStore();

const handleSubmit = async (values: any) => {
  const res = await userLogin(values);
  // 登录成功，跳转到主页
  if (res.data.code === 0 && res.data.data) {
    await loginUserStore.fetchLoginUser();
    message.success("登录成功");
    // 跳转到主页
    router.push({
      path: "/",
      replace: true,
    });
  } else {
    message.error("登录失败");
  }
};

const onFinishFailed = (errorInfo: any) => {
  console.log("Failed:", errorInfo);
};
</script>

<style scoped>
#userLoginPage .title {
  text-align: center;
  margin-bottom: 16px;
}
</style>
