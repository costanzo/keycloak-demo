'use client'
import {useEffect, useRef} from "react";
import {oauth2Authorization} from "@/api/auth";
import {useSearchParams} from "next/navigation";
import {setAccessToken, setSessionState} from "@/store/auth";

const AuthCallbackPage = () => {
  const params = useSearchParams();
  const requestingRef = useRef<boolean>(false);

  useEffect(() => {
    console.log('auth callback page')
    const code = params.get('code');
    const state = params.get('state');
    const sessionState = params.get('session_state');
    console.log('code:', code);
    console.log('iss:', params.get('iss'));
    console.log('session_state:', sessionState)
    if (code && state && sessionState && !requestingRef.current) {
      requestingRef.current = true
      oauth2Authorization({code: code, state: state}).then((rsp) => {
        console.log('get access token rsp', rsp)
        setAccessToken(rsp.token)
        setSessionState(sessionState)
        window.location.href = '/'
      }).finally(() => {
        requestingRef.current = false
      });
    }
  }, []);
  return (
    <div>
      <p>This is the callback page</p>
    </div>
  )
}

export default AuthCallbackPage;