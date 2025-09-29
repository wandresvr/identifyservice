package com.flowlite.identifyservice.domain.entities;
import com.flowlite.identifyservice.domain.valueobjects.Email;
import com.flowlite.identifyservice.domain.valueobjects.Password;
import com.flowlite.identifyservice.domain.valueobjects.Username;
import lombok.Data;
import lombok.Builder;

import java.util.UUID;

@Data
@Builder
public class User {

    private UUID id;                      // Identidad única
    private Username username;            // VO
    private Email email;                  // VO
    private Password password;            // VO (encriptada)
    private Role role;                    // Entidad/enum
    private boolean active;               // Estado de la cuenta

    public void deactivate() {
        this.active = false;
    }
    public void changeRole(Role newRole) {
        this.role = newRole;
    }

}
