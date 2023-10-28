package co.edu.unicauca.SIRENABackend.security.common.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PermissionEnum {

    ADMIN_READ,
    ADMIN_CREATE,
    ADMIN_UPDATE,
    ADMIN_DELETE,

    DOCENTE_READ,
    DOCENTE_CREATE,
    DOCENTE_UPDATE,
    DOCENTE_DELETE,

    COORDINADOR_READ,
    COORDINADOR_CREATE,
    COORDINADOR_UPDATE,
    COORDINADOR_DELETE;
}
