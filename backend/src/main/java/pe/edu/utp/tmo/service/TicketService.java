package pe.edu.utp.tmo.service;
import java.util.*;
import pe.edu.utp.tmo.dto.request.*;
import pe.edu.utp.tmo.dto.response.TicketResponse;

public interface TicketService{
    TicketResponse abrirTicket(String correo,TicketCreateRequest r);
    TicketResponse cerrarTicket(String correo,Long id,TicketCloseRequest r);
    List<TicketResponse> listarMisTickets(String correo);
}