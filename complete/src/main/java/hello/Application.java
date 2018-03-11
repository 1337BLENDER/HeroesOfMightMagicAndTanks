package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(UnitsService unitsService,RaceService raceService,BuildingsService buildingsService,CitiesService citiesService, CharactersService charactersService,AbilitiesService abilitiesService, ArmyService armyService, UnitsInArmyService unitsInArmyService, LocationsService locationsService,UsersService usersService, FriendsService friendsService, RoleRepository roleRepository, AppUserRepository appUserRepository ) {
		return (args) -> {
/*
			raceService.saveOrUpdate(new Race("Эльфы","/icons/races/elf.jpg"));
            raceService.saveOrUpdate(new Race("Орки","/icons/races/orc.jpg"));
            raceService.saveOrUpdate(new Race("Гномы","/icons/races/dwarf.jpg"));
            raceService.saveOrUpdate(new Race("Люди","/icons/races/human.jpg"));

			//raceService.saveOrUpdate(new Race("Хоббиты"));

            List<Race> races=new ArrayList<>();
            for (Race race:raceService.getAll()) {
                races.add(race);
            }
            log.info("Races");
            log.info("---------------------------------------");
            for (int i=0;i<races.size();i++){
                log.info(races.get(i).toString());
            }

            unitsService.saveOrUpdate(new Units("Эпический крушитель",10,"ближний бой",666,0,0,228,15,100500,races.get(1),"/icons/units/epic.jpg","/icons/units/battle_epic.jpg"));
            unitsService.saveOrUpdate(new Units("Эльфийский лучник",3,"дальний бой",200,10,4,200,15,300,races.get(0),"/icons/units/elf_shooter.jpg","/icons/units/battle_elf_shooter.jpg"));
            unitsService.saveOrUpdate(new Units("dhg",100,"ближний бой",100,0,0,208,1,100500,races.get(1),"/icons/units/dhg.jpg","/icons/units/battle_dhg.jpg"));
            unitsService.saveOrUpdate(new Units("Гномий воин",43,"ближний бой",120,0,0,212,5,234,races.get(2),"/icons/units/dwarf.jpg","/icons/units/battle_dwarf.jpg"));
			Iterable<Units> unitsIter=unitsService.getAll();
			List<Units>units=new ArrayList<>();
            for (Units unit:unitsIter) {
                units.add(unit);
            }
            log.info("Units");
            log.info("---------------------------------------");
            for(int i=0;i<units.size();i++){
                log.info(units.get(i).toString());
            }

            citiesService.saveOrUpdate(new Cities("Мордор"));
            citiesService.saveOrUpdate(new Cities("Город лесных эльфов"));

            List<Cities> cities=new ArrayList(){};
            for(Cities city:citiesService.getAll()){
                cities.add(city);
            }

            buildingsService.saveOrUpdate(new Buildings("Дом эпических крушителей",1,10,"военное",null,20,races.get(1),units.get(0),cities.get(0)));
            buildingsService.saveOrUpdate(new Buildings("Дерево эльфийских лучников",1,10,"военное",null,20,races.get(0),units.get(1),cities.get(0)));
            buildingsService.saveOrUpdate(new Buildings("Хата ороков",1,0,"финансовое",2000,null,races.get(1),null,cities.get(1)));
            buildingsService.saveOrUpdate(new Buildings("Дворец эльфов",1,0,"финансовое",2000,null,races.get(0),null,cities.get(1)));

            log.info("Buildings");
            log.info("---------------------------------------");
            for(Buildings building:buildingsService.getAll()){
                log.info(building.toString());
            }

            log.info("Cities");
            log.info("---------------------------------------");
            for(Cities city:citiesService.getAll()){
                log.info(city.toString());
            }

            Characters char1=new Characters("Леголас","Лучник",races.get(1),"/icons/characters/legolas.jpg","icons/characters/battle_legolas.jpg");
            char1.addAbility(new Abilities("Выстрел","нанесение урона",2,50,"description description description description description description ","enemy"));
            char1.addAbility(new Abilities("Мощный выстрел","нанесение урона",4,150,"description description description description description description ","enemy"));
            char1.addAbility(new Abilities("Меткий выстрел","нанесение урона",7,500,"description description description description description description ","enemy"));

            Characters char2=new Characters("Саурон","Маг",races.get(0),"/icons/characters/sauron.jpg","icons/characters/battle_sauron.jpg");
            char2.addAbility(new Abilities("Удар","нанесение урона",2,60,"description description description description description description ","enemy"));
            char2.addAbility(new Abilities("Удар Дубиной","нанесение урона",5,200,"description description description description description description ","enemy"));
            char2.addAbility(new Abilities("Сокрушающий удар","нанесение урона",9,630,"description description description description description description ","enemy"));

            charactersService.saveOrUpdate(char1);
            charactersService.saveOrUpdate(char2);

            List<Characters> characters = new ArrayList<>();
            for(Characters character:charactersService.getAll()){
                characters.add(character);
            }

            //abilitiesService.saveOrUpdate();
            //abilitiesService.saveOrUpdate();
            //abilitiesService.saveOrUpdate();
            //abilitiesService.saveOrUpdate();
            //abilitiesService.saveOrUpdate(new Abilities("Удар Дубиной","нанесение урона",3,5,200));
            //abilitiesService.saveOrUpdate();

            List<Abilities> abilities = new ArrayList<>();
            for(Abilities ability: abilitiesService.getAll()){
                abilities.add(ability);
            }
            log.info("Abilities");
            log.info("---------------------------------------");
            for(int i=0;i<abilities.size();i++){
                log.info(abilities.get(i).toString());
            }

            log.info("Characters");
            log.info("---------------------------------------");
            for(Characters character:charactersService.getAll()){
                log.info(character.toString());
            }

            armyService.saveOrUpdate(new Army());
            armyService.saveOrUpdate(new Army());
            armyService.saveOrUpdate(new Army());
            armyService.saveOrUpdate(new Army());

            List<Army> armies = new ArrayList<>();

            for(Army army: armyService.getAll()){
                armies.add(army);
            }

            unitsInArmyService.saveOrUpdate(new UnitsInArmy(20,armies.get(0),units.get(0)));
            unitsInArmyService.saveOrUpdate(new UnitsInArmy(20,armies.get(1),units.get(1)));
            unitsInArmyService.saveOrUpdate(new UnitsInArmy(20,armies.get(2),units.get(0)));
            unitsInArmyService.saveOrUpdate(new UnitsInArmy(10,armies.get(3),units.get(0)));
            unitsInArmyService.saveOrUpdate(new UnitsInArmy(10,armies.get(3),units.get(1)));

            armies.clear();
            for(Army army: armyService.getAll()){
                armies.add(army);
            }

            log.info("Armies");
            log.info("---------------------------------------");
            for(Army army:armyService.getAll()){
                log.info(army.toString());
            }
            log.info(armyService.getFirstById(1).toString());

            locationsService.saveOrUpdate(new Locations("Засада Орков",1,100,1000,armies.get(2)));
            locationsService.saveOrUpdate(new Locations("Странный союз",1,100,1000,armies.get(3)));

            List<Locations> locations = new ArrayList<>();
            for(Locations location:locationsService.getAll()){
                Collection<UnitsInArmy> units1=new ArrayList<>();
                for(UnitsInArmy unitsInArmy:unitsInArmyService.getAllByArmy(location.getArmy())){
                    units1.add(unitsInArmy);
                }
                location.getArmy().setUnits(units1);
                location.getArmy().calculatePower();
                locations.add(location);
            }

            log.info("Locations");
            log.info("---------------------------------------");
            for(int i=0;i<locations.size();i++){
                log.info(locations.get(i).toString());
            }

            Users userszzz=new Users("admin","1234","jabber1",50.0,20,characters.get(0),armies.get(0));

            usersService.saveOrUpdate(userszzz);
            //SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin","doesn't matter", AuthorityUtils.createAuthorityList("ROLE_USER")));
            usersService.saveOrUpdate(new Users("nick","4321","jabber2",66.6666,15,characters.get(1),armies.get(1)));

            List<Users> users=new ArrayList<>();

            log.info("Users");
            log.info("---------------------------------------");
            for(Users user:usersService.getAll()){
                log.info(user.toString());
                users.add(user);
            }
			log.info("");
            friendsService.saveOrUpdate(new Friends(users.get(0),users.get(1)));
            friendsService.saveOrUpdate(new Friends(users.get(1),users.get(0)));

            log.info("Friends");
            log.info("---------------------------------------");
            for(Friends friend:friendsService.getAll()){
                log.info(friend.toString());
            }

            log.info("--------------------------------------");
            log.info(buildingsService.getById(1).getUnit().toString());
*/

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin","doesn't matter", AuthorityUtils.createAuthorityList("ROLE_USER")));
		};
	}

}
