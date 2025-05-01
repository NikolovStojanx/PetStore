package mk.ukim.finki.petstore.service.impl;

import mk.ukim.finki.petstore.functionalities.dataGenerator.DataGenerator;
import mk.ukim.finki.petstore.model.dto.UserDTO;
import mk.ukim.finki.petstore.model.exceptions.PetHasOwnerException;
import mk.ukim.finki.petstore.model.exceptions.UserLowBudgetException;
import mk.ukim.finki.petstore.model.User;
import mk.ukim.finki.petstore.model.abstractions.Pet;
import mk.ukim.finki.petstore.repository.jpa.UserRepository;
import mk.ukim.finki.petstore.service.HistoryLogService;
import mk.ukim.finki.petstore.service.PetService;
import mk.ukim.finki.petstore.service.UserService;
import mk.ukim.finki.petstore.web.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PetService petService;
    private final HistoryLogService historyLogService;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PetService petService, HistoryLogService historyLogService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.petService = petService;
        this.historyLogService = historyLogService;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> createUsers() {
        List<User> usersToSave = DataGenerator.generateUsers(12);
        userRepository.saveAll(usersToSave);

        return usersToSave;
    }

    @Override
    public List<UserDTO> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public void buy() {
//        List<User> allUsers = userRepository.findAll();
//        List<Pet> allPets = petService.listPets();
//        Pet pet1 = null;
//
//        for (User user : allUsers) {
//            for (Pet pet : allPets) {
//                try {
//                    pet1 = user.buyPet(pet);
//                    if (pet1 != null) {
//                        successfullyBoughtCount++;
//                        petService.save(pet1);
//                        userRepository.save(user);
//                        break;
//                    }
//                } catch (PetHasOwnerException  | UserLowBudgetException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//
//        historyLogService.saveHistoryLog(successfullyBoughtCount, allUsers.size() - successfullyBoughtCount);
//    }
    @Override
    public void buy() {
        List<User> allUsers = userRepository.findAll();
        int successfullyBoughtCount = 0;

        for (User user : allUsers) {
            boolean successfulBoughtPet = this.buyPetForUser(user);
            if(successfulBoughtPet)
                successfullyBoughtCount++;
        }


        historyLogService.saveHistoryLog(successfullyBoughtCount, allUsers.size() - successfullyBoughtCount);
    }

    @Override
    public boolean buyPetForUser(User user) {
        List<Pet> allPets = petService.listPets();

        for (Pet pet : allPets) {
            Pet pet1 = null;
            try {
                pet1 = user.buyPet(pet);
                if (pet1 != null) {
                    petService.save(pet1);
                    userRepository.save(user);
                    return true;
                }
            } catch (PetHasOwnerException  | UserLowBudgetException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

}
