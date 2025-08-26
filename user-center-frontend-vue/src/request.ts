import axios from "axios";

const myAxios = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 10000,
  withCredentials: true,
});
// 将 myAxios 导出，以便在其他文件中import使用
export default myAxios;

// 添加请求拦截器
myAxios.interceptors.request.use(
  function (config) {
    // 在发送请求之前做些什么
    return config;
  },
  function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

// 添加响应拦截器
myAxios.interceptors.response.use(
  function (response) {
    // 对响应数据做点什么
    console.log("Response received:", response);

    const { data } = response;
    console.log("Response data:", data);
    // 未登陆
    if (data.code == 40100) {
      if (
        !response.request.responseURL.includes("user/current") &&
        !window.location.pathname.includes("/user/login")
      ) {
        window.location.href = "/user/login?redirect=${window.location.href}";
      }
    }
    return response;
  },
  function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
  }
);
