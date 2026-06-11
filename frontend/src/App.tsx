import {Navigate,Route,Routes} from 'react-router-dom';
import {currentRole,getToken} from './api/client';
import Layout from './components/Layout';
import LoginPage from './pages/LoginPage';
import AnalystDashboard from './pages/AnalystDashboard';
import TicketForm from './pages/TicketForm';
import ActiveTicketPage from './pages/ActiveTicketPage';
import SupervisorDashboard from './pages/SupervisorDashboard';
import ReportsPage from './pages/ReportsPage';
function Protected({roles,children}:{roles?:string[];children:JSX.Element}){const token=getToken();const rol=currentRole();if(!token)return <Navigate to="/login" replace/>;if(roles&&rol&&!roles.includes(rol))return <Navigate to="/" replace/>;return children;}
function Home(){return currentRole()==='ANALISTA'?<Navigate to="/tickets" replace/>:<Navigate to="/dashboard" replace/>}
export default function App(){return <Routes><Route path="/login" element={<LoginPage/>}/><Route path="/" element={<Protected><Layout/></Protected>}><Route index element={<Home/>}/><Route path="tickets" element={<Protected roles={['ANALISTA']}><AnalystDashboard/></Protected>}/><Route path="tickets/new" element={<Protected roles={['ANALISTA']}><TicketForm/></Protected>}/><Route path="tickets/:id/close" element={<Protected roles={['ANALISTA','SUPERVISOR','GERENTE','ADMINISTRADOR']}><ActiveTicketPage/></Protected>}/><Route path="dashboard" element={<Protected roles={['SUPERVISOR','GERENTE','ADMINISTRADOR']}><SupervisorDashboard/></Protected>}/><Route path="reports" element={<Protected roles={['SUPERVISOR','GERENTE','ADMINISTRADOR']}><ReportsPage/></Protected>}/></Route></Routes>}
