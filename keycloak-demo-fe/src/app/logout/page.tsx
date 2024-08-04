'use client'

import {useEffect} from "react";
import {clearAccessToken} from "@/store/auth";

const LogoutPage = () => {
  useEffect(() => {
    console.log('logout page')
    clearAccessToken()
    window.location.href = 'http://localhost:3000'
  }, []);
  return (
    <div>
      <p>This is the logout page</p>
    </div>
  )
}

export default LogoutPage;