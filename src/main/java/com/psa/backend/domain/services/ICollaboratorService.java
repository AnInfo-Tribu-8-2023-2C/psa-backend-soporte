package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.Collaborator;
import java.util.Collection;

public interface ICollaboratorService {

    public Collection<Collaborator> getCollaborators();

    public Collaborator getCollaboratorById(Long id);
}
