const API_BASE_URL = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080/api';
export const getToken=()=>sessionStorage.getItem('tmo_token');
export const currentRole=()=>sessionStorage.getItem('tmo_rol');
export const currentName=()=>sessionStorage.getItem('tmo_nombre');
export function setSession(token:string,rol:string,nombre:string,area:string){sessionStorage.setItem('tmo_token',token);sessionStorage.setItem('tmo_rol',rol);sessionStorage.setItem('tmo_nombre',nombre);sessionStorage.setItem('tmo_area',area);}
export function clearSession(){sessionStorage.clear();}
async function request<T>(path:string,options:RequestInit={}):Promise<T>{const headers=new Headers(options.headers);headers.set('Content-Type','application/json');const token=getToken();if(token)headers.set('Authorization',`Bearer ${token}`);const res=await fetch(`${API_BASE_URL}${path}`,{...options,headers});if(res.status===401){clearSession();window.location.href='/login';throw new Error('Sesión expirada');}if(!res.ok){const err=await res.json().catch(()=>({message:'Error inesperado'}));throw new Error(err.message??'Error inesperado');}return res.json();}
export const api={get:<T>(p:string)=>request<T>(p),post:<T>(p:string,b:unknown)=>request<T>(p,{method:'POST',body:JSON.stringify(b)}),put:<T>(p:string,b:unknown)=>request<T>(p,{method:'PUT',body:JSON.stringify(b)}),download:async(p:string)=>{const h=new Headers();const t=getToken();if(t)h.set('Authorization',`Bearer ${t}`);const r=await fetch(`${API_BASE_URL}${p}`,{headers:h});if(!r.ok)throw new Error('No se pudo descargar');return r.blob();}};
