import {useEffect, useRef} from "react";
import {getAccessToken} from "@/store/auth";
import {GetSessionRsp, oauth2Session} from "@/api/auth";

const useKeycloak = () => {
  const sessionIntervalRef = useRef<NodeJS.Timeout>()
  useEffect(() => {
    if (typeof window === 'undefined') return null;
    const accessToken = getAccessToken();
    if (accessToken === '') {
      const state = Math.random().toString(36).substring(7);
      const url = 'http://localhost:8080/realms/demo/protocol/openid-connect/auth?client_id=nextjs-demo&response_type=code' +
        "&redirect_uri=" + encodeURIComponent(window.location.href+'auth/callback') + "&state=" + state + "&scope=openid"
      console.log(url)
      window.location.href = url;
    } else {
      // check session
      console.log('start checking session')
      sessionIntervalRef.current = setInterval(() => {
        console.log('check session')
        oauth2Session().then((rsp: GetSessionRsp )=> {
          console.log('session rsp', rsp)
          if (!rsp.valid) {
            // logout
            window.location.href = '/logout'
          }
        });
      }, 10000)
    }
    return () => {
      if (sessionIntervalRef.current) {
        clearInterval(sessionIntervalRef.current)
      }
    }
  },[])

  const logout = () => {
    const baseUrl = 'http://localhost:8080/realms/demo/protocol/openid-connect/logout';
    const redirectUri = window.location.href + 'logout';
    const idTokenHint = getAccessToken()
    window.location.href = baseUrl + '?post_logout_redirect_uri=' + encodeURIComponent(redirectUri) + '&' + 'id_token_hint' + '=' + idTokenHint;
  }

  return {logout}
}

export default useKeycloak;