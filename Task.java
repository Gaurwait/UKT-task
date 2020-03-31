@RestController
public class UserResource {

    @Autowired
    private UserDAOservice userDAOService;

    @GetMapping("/users")
    public MappingJacksonValue users() {
        List<User> users = userDAOService.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name");

        FilterProvider filters = new SimpleFilterProvider().addFilter(
                "UserBirthDateFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);

        mapping.setFilters(filters);

        return mapping;
    }
}


//test change
//test change 1

@GetMapping("/users/{id}")
public EntityModel<User> users(@PathVariable Integer id) {
    User user = userDAOService.findById(id);
    if (user == null) {
        throw new ResourceNotFoundException("id-" + id);
    }

    EntityModel<User> model = new EntityModel<>(user);
    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).users());
    model.add(linkTo.withRel("all-users"));

    //how do I combine EntityModel with filtering?
    return model;
}