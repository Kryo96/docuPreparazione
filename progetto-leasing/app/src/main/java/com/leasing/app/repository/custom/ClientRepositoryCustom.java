package com.leasing.app.repository.custom;

import com.leasing.app.model.Client;

import java.util.List;

public interface ClientRepositoryCustom {
    List<Client> searchClients(String term);
}
