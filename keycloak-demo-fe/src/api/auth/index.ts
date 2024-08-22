import request from "@/api/request";

export interface GetAccessTokenReq {
  code: string
  state: string
}

export interface GetAccessTokenRsp {
  lastName: string
  firstName: string
  email: string
  token: string
}

export interface GetSessionRsp {
  valid: boolean
}

export interface StateRsp {
  state: string
}

export const oauth2Authorization = async (req: GetAccessTokenReq): Promise<GetAccessTokenRsp> => {
  return request.request({
    url: '/auth/oauth2/authorization_code',
    method: 'post',
    data: req
  })
}

export const oauth2Session = async (): Promise<GetSessionRsp> => {
  return request.request({
    url: '/auth/oauth2/session',
    method: 'get',
  })
}

export const oauth2State = async (): Promise<StateRsp> => {
  return request.request({
    url: '/auth/oauth2/state',
    method: 'post',
  })
}