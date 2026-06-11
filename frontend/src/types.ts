export type Rol='ANALISTA'|'SUPERVISOR'|'GERENTE'|'ADMINISTRADOR';
export interface AuthResponse{token:string;rol:Rol;nombre:string;area:string;expiresAt:string}
export interface TicketResponse{id:number;codigo:string;tipoCaso:string;estado:string;referencia:string;tipificacion?:string;fechaInicio:string;fechaFin?:string;tmoSegundos?:number}
export interface TipificacionResponse{id:number;nombre:string}
export interface DashboardResponse{abiertos:number;cerrados:number;tmoPromedioSegundos:number;alertasSla:number;porTipo:{tipoCaso:string;total:number;promedioSegundos:number}[]}
