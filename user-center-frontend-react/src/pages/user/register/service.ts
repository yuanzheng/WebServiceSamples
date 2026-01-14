import request  from '@/plugins/globalRequest';

export interface UserRegisterParams {
  userAccount: string;
  userPassword: string;
  checkPassword: string;
  //mobile: string;
  //captcha: string;
  //prefix: string;
}

/** 注册接口 POST /api/user/register */
export async function register(params: UserRegisterParams) {
  return request('/api/user/register', {
    method: 'POST',
    data: params,
  });
}
