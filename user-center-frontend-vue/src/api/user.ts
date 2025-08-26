import myAxios from "@/request";

/**
 * 用户注册
 * @param params
 * @returns
 */
export const userRegister = async (params: any) => {
  return await myAxios.request({
    url: "/api/user/register",
    method: "POST",
    data: params,
  });
};

/**
 * 用户登录
 * @param params
 * @returns
 */
export const userLogin = async (params: any) => {
  return await myAxios.request({
    url: "/api/user/login",
    method: "POST",
    data: params,
  });
};

/**
 * 用户登出
 * @param params
 * @returns
 */
export const userLogout = async (params: any) => {
  return await myAxios.request({
    url: "/api/user/logout",
    method: "POST",
    data: params,
  });
};

/**
 * 获取当前用户信息
 * @returns 获取用户信息
 */
export const getUserInfo = async () => {
  return await myAxios.request({
    url: "/api/user/info",
    method: "GET",
  });
};

/**
 * 获取用户列表
 * @param username
 * @returns
 */
export const searchUsers = async (username: any) => {
  return await myAxios.request({
    url: "/api/user/list",
    method: "GET",
    params: {
      username,
    },
  });
};

/**
 * 删除用户
 * @param id
 * @returns
 */
export const deleteUser = async (id: string) => {
  return await myAxios.request({
    url: "/api/user/delete",
    method: "POST",
    data: id,
    // 关键点：指定请求头为 JSON
    headers: {
      "Content-Type": "application/json",
    },
  });
};
