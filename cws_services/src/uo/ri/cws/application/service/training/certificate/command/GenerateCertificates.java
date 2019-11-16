package uo.ri.cws.application.service.training.certificate.command;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;

/**
 * TODO 1 - Generar certificados
 *
 * Manager Main: 4 - 4 Periódicamente se ejecutará el proceso que se encarga de
 * generar los nuevos certificados para los mecánicos que cumplan los requisitos
 * especificados en Generación de certificados. No requiere interacción del
 * usuario. En un despliegue real este proceso sería lanzado como una tarea
 * programada del sistema (cron, Windows task scheduler, etc.). Por simplicidad
 * se deja como una opción del menú del administrador.
 */
public class GenerateCertificates implements Command<Integer> {

	@Override
	public Integer execute() throws BusinessException {
		throw new RuntimeException("Not yet implemented.");
	}

}
