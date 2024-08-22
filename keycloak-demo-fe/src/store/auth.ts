const accessTokenKey = 'accessToken';
const idTokenKey = 'idToken';
const sessionStateKey = 'sessionState';


export const getSessionState = () => {
  if (typeof window === 'undefined') return '';
  return localStorage.getItem(sessionStateKey) || '';
}

export const setSessionState = (state: string) => {
  if (typeof window === 'undefined') return;
  localStorage.setItem(sessionStateKey, state);
}

export const getAccessToken = () => {
  if (typeof window === 'undefined') return '';
  return localStorage.getItem(accessTokenKey) || '';
}

export const setAccessToken = (token: string) => {
  if (typeof window === 'undefined') return;
  localStorage.setItem(accessTokenKey, token);
}

export const clearAccessToken = () => {
  localStorage.removeItem(accessTokenKey);
}

export const getIdToken = () => {
  if (typeof window === 'undefined') return '';
  return localStorage.getItem(idTokenKey) || '';
}

export const setIdToken = (token: string) => {
  if (typeof window === 'undefined') return;
  localStorage.setItem(idTokenKey, token);
}

export const clearIdToken = () => {
  localStorage.removeItem(idTokenKey);
}