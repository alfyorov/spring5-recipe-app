package guru.springframework.services;

import java.io.IOException;
import java.util.Optional;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (!recipeOptional.isPresent()) {
            return;
        }

        Recipe recipe = recipeOptional.get();
        try {
            recipe.setImage(file.getBytes());
            recipeRepository.save(recipe);
        } catch (IOException e) {
            System.out.println("Error occurred");
        }
    }
}
