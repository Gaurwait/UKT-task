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