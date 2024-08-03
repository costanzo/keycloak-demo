import {getAccessToken} from "@/store/auth";

const AuthFilter = ({ children }) => {
  if (typeof window === 'undefined') return null;
  const accessToken = getAccessToken();
  if (accessToken === '') {
    window.location.href = 'http://localhost:8080/realms/demo/protocol/openid-connect/auth?client_id=nextjs-demo&response_type=code';
  }
  return null;
}

export default AuthFilter;