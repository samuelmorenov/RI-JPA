package uo.ri.cws.application.service.training.certificate;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateService;

public class CertificateServiceImpl implements CertificateService {

	@Override
	public int generateCertificates() throws BusinessException {
		// TODO 1.1.1 - Generar certificados
		/*
		 * Manager Main: 4 - 4 Periódicamente se ejecutará el proceso que se encarga de
		 * generar los nuevos certificados para los mecánicos que cumplan los requisitos
		 * especificados en Generación de certificados. No requiere interacción del
		 * usuario. En un despliegue real este proceso sería lanzado como una tarea
		 * programada del sistema (cron, Windows task scheduler, etc.). Por simplicidad
		 * se deja como una opción del menú del administrador.
		 */
		throw new RuntimeException("Not yet implemented.");
	}

}
