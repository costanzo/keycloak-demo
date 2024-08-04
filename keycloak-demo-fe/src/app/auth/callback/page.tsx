'use client'
import {useEffect, useRef} from "react";
import {oauth2Authorization} from "@/api/auth";
import {useSearchParams} from "next/navigation";
import {setAccessToken} from "@/store/auth";

const AuthCallbackPage = () => {
  const params = useSearchParams();
  const requestingRef = useRef<boolean>(false);

  useEffect(() => {
    console.log('auth callback page')
    console.log('session_state:', params.get('session_state'))
    console.log('iss:', params.get('iss'));
    console.log('code:', params.get('code'));
    const code = params.get('code');
    if (code && !requestingRef.current) {
      requestingRef.current = true
      oauth2Authorization({code: code}).then((rsp) => {
        console.log('get access token rsp', rsp)
        setAccessToken(rsp.token)
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