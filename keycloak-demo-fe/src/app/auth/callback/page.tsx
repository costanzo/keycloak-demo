'use client'
import {useEffect} from "react";
import {getAccessToken} from "@/api/auth";
import {useSearchParams} from "next/navigation";

const AuthCallbackPage = () => {
  const params = useSearchParams();

  useEffect(() => {
    console.log('auth callback page')
    console.log('session_state:', params.get('session_state'))
    console.log('iss:', params.get('iss'));
    console.log('code:', params.get('code'));
    getAccessToken({}).then((rsp) => {
      console.log('get access token rsp', rsp)
    })
  }, []);
  return (
    <div>
      <p>This is the callback page</p>
    </div>
  )
}

export default AuthCallbackPage;