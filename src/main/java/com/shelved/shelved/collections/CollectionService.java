package com.shelved.shelved.collections;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionService {

    private final CollectionRepository collectionRepository;


    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    //Get All Collections
    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    //Get Collection
    public Collection getCollectionById(Integer id) {
        Optional<Collection> optionalCollection = collectionRepository.findById(id);

        if (optionalCollection.isPresent()) {
            return optionalCollection.get();
        }
        return null;
    }

    //Post Collection
    public Collection saveCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    //Put Collection
    public Collection updateCollection(Collection updatedCollection) {
        Optional<Collection> optionalCollection = collectionRepository.findById(updatedCollection.getId());

        if (optionalCollection.isPresent()) {
            Collection collection = optionalCollection.get();
            collection.setName(updatedCollection.getName());
            collection.setDescription(updatedCollection.getDescription());
            collection.setImageUrl(updatedCollection.getImageUrl());
            return collectionRepository.save(collection);
        }
        return null;
    }

    //Delete Collection
    public void deleteCollection(Integer id) {
        Optional<Collection> optionalCollection = collectionRepository.findById(id);
        if (optionalCollection.isPresent()) {
            collectionRepository.delete(optionalCollection.get());
        }
    }
}
