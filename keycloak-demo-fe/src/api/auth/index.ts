import request from "@/api/request";

export interface GetAccessTokenReq {

}

export interface GetAccessTokenRsp {

}


export const getAccessToken = async (req: GetAccessTokenReq): Promise<GetAccessTokenRsp> => {
  return request.request({
    url: '/auth/oauth2',
    method: 'post',
    // data: req
  })
}