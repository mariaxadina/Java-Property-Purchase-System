package service;
import model.*;
import java.util.*;
import repository.ContractRepository;

public class ContractService {
    private ContractRepository contractRepository;
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }
    public void addContract(Contract contract) {
        contractRepository.addContract(contract);
    }
}
