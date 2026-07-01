package code.adagedo.serviceone;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/api/v1/posts/1")
    public ResponseEntity<Posts> postsResponseEntity(){
        Posts posts = new Posts("new Post", "this is a new post", "Samuel");
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
}
