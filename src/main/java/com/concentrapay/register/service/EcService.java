package com.concentrapay.register.service;

import com.concentrapay.register.exception.ECAlreadyExistsException;
import com.concentrapay.register.exception.ECNotFoundException;
import com.concentrapay.register.model.Address;
import com.concentrapay.register.model.EC;
import com.concentrapay.register.model.dto.EcDTO;
import com.concentrapay.register.repository.AddressRepository;
import com.concentrapay.register.repository.ECRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EcService {

    private final ECRepository ecRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    @Autowired
    public EcService(ECRepository ecRepository, AddressRepository addressRepository, ModelMapper ecMapper) {
        this.ecRepository = ecRepository;
        this.addressRepository = addressRepository;
        this.mapper = ecMapper;
    }

    public ResponseEntity<EC> create(EcDTO ecDTO) {

        Optional<EC> ecOptional = ecRepository.findByCnpj(ecDTO.getCnpj());

        if (ecOptional.isPresent()) {
            throw new ECAlreadyExistsException("EC já está cadastrado");
        }

        EC ecToSave = mapper.map(ecDTO, EC.class);
        Address addressToSave = ecToSave.getAddress();

        ecToSave.setAddress(saveOrGetAddress(addressToSave));

        EC ec = ecRepository.save(ecToSave);
        log.info("Estabelecimento: " + ec.getCnpj() + " salvo");

        return new ResponseEntity<>(ec, HttpStatus.CREATED);
    }

    private Address saveOrGetAddress(Address addressToSave) {
        Optional<Address> optionalAddress = addressRepository.
                findByStreetAndNumberAndCity(addressToSave.getStreet(), addressToSave.getNumber(), addressToSave.getCity());

        if (optionalAddress.isEmpty()) {
            return addressRepository.save(addressToSave);
        } else {
            return optionalAddress.get();
        }
    }

    public ResponseEntity<List<EC>> getAllEcs() {

        List<EC> result = ecRepository.findAll();

        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    public ResponseEntity<EC> getEc(Long id) {
        Optional<EC> optionalEC = ecRepository.findById(id);

        if (optionalEC.isEmpty()) {
            throw new ECNotFoundException("EC não encontrado");
        }

        return ResponseEntity.ok(optionalEC.get());
    }

    public Optional<EC> getEcByCnpj(String cnpj) {
        return ecRepository.findByCnpj(cnpj);
    }

    public ResponseEntity<EC> update(Long id, EcDTO ecDTO) {
        Optional<EC> optionalEC = ecRepository.findById(id);

        if (optionalEC.isEmpty()) {
            throw new ECNotFoundException("EC não encontrado");
        }

        EC ecToUpdate = mapper.map(ecDTO, EC.class);
        ecToUpdate.setAddress(saveOrGetAddress(ecToUpdate.getAddress()));
        ecToUpdate.setId(optionalEC.get().getId());

        EC ecUpdated = ecRepository.save(ecToUpdate);

        return ResponseEntity.ok(ecUpdated);
    }
}
