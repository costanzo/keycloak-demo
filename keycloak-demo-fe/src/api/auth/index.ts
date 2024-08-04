import request from "@/api/request";

export interface GetAccessTokenReq {
  code: string
}

export interface GetAccessTokenRsp {
  lastName: string
  firstName: string
  email: string
  token: string
}


export const oauth2Authorization = async (req: GetAccessTokenReq): Promise<GetAccessTokenRsp> => {
  return request.request({
    url: '/auth/oauth2/authorization_code',
    method: 'post',
    data: req
  })
}