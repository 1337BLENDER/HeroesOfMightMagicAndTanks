package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(UnitsService unitsService,RaceService raceService,BuildingsService buildingsService,CitiesService citiesService,BuildingsInCitiesService buildingsInCitiesService, CharactersService charactersService,AbilitiesService abilitiesService, CharactersAbilitiesService charactersAbilitiesService, ArmyService armyService, UnitsInArmyService unitsInArmyService, LocationsService locationsService,UsersService usersService ) {
		return (args) -> {
			raceService.saveOrUpdate(new Race("Эльфы"));
            raceService.saveOrUpdate(new Race("Орки"));
            raceService.saveOrUpdate(new Race("Гномы"));
            raceService.saveOrUpdate(new Race("Люди"));

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

            unitsService.saveOrUpdate(new Units("Эпический крушитель",10,"ближний бой",666,null,null,228,1488,100500,races.get(1)));
            unitsService.saveOrUpdate(new Units("Эльфийский лучник",3,"дальний бой",200,10,15,200,1300,300,races.get(0)));
            unitsService.saveOrUpdate(new Units("dhg",100,"ближний бой",100,null,null,208,1488,100500,races.get(1)));
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

            buildingsService.saveOrUpdate(new Buildings("Дом эпических крушителей",1,10,"военное",null,20,races.get(1),units.get(0)));
            buildingsService.saveOrUpdate(new Buildings("Дерево эльфийских лучников",1,10,"военное",null,20,races.get(0),units.get(1)));
            buildingsService.saveOrUpdate(new Buildings("Хата ороков",1,0,"финансовое",2000,null,races.get(1),null));
            buildingsService.saveOrUpdate(new Buildings("Дворец эльфов",1,0,"финансовое",2000,null,races.get(0),null));

            List<Buildings> buildings=new ArrayList<>();
            for (Buildings building:buildingsService.getAll()) {
                buildings.add(building);
            }
            log.info("Buildings");
            log.info("---------------------------------------");
            for(int i=0;i<buildings.size();i++){
                log.info(buildings.get(i).toString());
            }

            citiesService.saveOrUpdate(new Cities("Мордор"));
            citiesService.saveOrUpdate(new Cities("Город лесных эльфов"));

            List<Cities> cities=new ArrayList(){};
            for(Cities city:citiesService.getAll()){
                cities.add(city);
            }

            buildingsInCitiesService.saveOrUpdate(new BuildingsInCities(cities.get(0),buildings.get(0)));
            buildingsInCitiesService.saveOrUpdate(new BuildingsInCities(cities.get(0),buildings.get(2)));
            buildingsInCitiesService.saveOrUpdate(new BuildingsInCities(cities.get(1),buildings.get(1)));
            buildingsInCitiesService.saveOrUpdate(new BuildingsInCities(cities.get(1),buildings.get(3)));

            for(int i=0;i<cities.size();i++){
                Collection<BuildingsInCities> buildings1=new ArrayList<>();
                for(BuildingsInCities buildingsInCities: buildingsInCitiesService.getAllByCity(cities.get(i))){
                    buildings1.add(buildingsInCities);
                }
                cities.get(i).setBuildings(buildings1);
            }
            log.info("Cities");
            log.info("---------------------------------------");
            for(int i=0;i<cities.size();i++){

                log.info(cities.get(i).toString());
            }

            charactersService.saveOrUpdate(new Characters("Леголас","Лучник",races.get(1)));
            charactersService.saveOrUpdate(new Characters("Саурон","Маг",races.get(0)));

            List<Characters> characters = new ArrayList<>();
            for(Characters character:charactersService.getAll()){
                characters.add(character);
            }

            abilitiesService.saveOrUpdate(new Abilities("Выстрел","нанесение урона",0,2,50));
            abilitiesService.saveOrUpdate(new Abilities("Мощный выстрел","нанесение урона",3,4,150));
            abilitiesService.saveOrUpdate(new Abilities("Меткий выстрел","нанесение урона",9,7,500));
            abilitiesService.saveOrUpdate(new Abilities("Удар","нанесение урона",0,2,60));
            abilitiesService.saveOrUpdate(new Abilities("Удар Дубиной","нанесение урона",3,5,200));
            abilitiesService.saveOrUpdate(new Abilities("Сокрушающий удар","нанесение урона",9,9,630));

            List<Abilities> abilities = new ArrayList<>();
            for(Abilities ability: abilitiesService.getAll()){
                abilities.add(ability);
            }
            log.info("Abilities");
            log.info("---------------------------------------");
            for(int i=0;i<abilities.size();i++){
                log.info(abilities.get(i).toString());
            }

            charactersAbilitiesService.saveOrUpdate(new CharactersAbilities(characters.get(0),abilities.get(0)));
            charactersAbilitiesService.saveOrUpdate(new CharactersAbilities(characters.get(0),abilities.get(1)));
            charactersAbilitiesService.saveOrUpdate(new CharactersAbilities(characters.get(0),abilities.get(2)));
            charactersAbilitiesService.saveOrUpdate(new CharactersAbilities(characters.get(1),abilities.get(3)));
            charactersAbilitiesService.saveOrUpdate(new CharactersAbilities(characters.get(1),abilities.get(4)));
            charactersAbilitiesService.saveOrUpdate(new CharactersAbilities(characters.get(1),abilities.get(5)));

            for(int i=0;i<characters.size();i++){
                Collection<CharactersAbilities> abilities1=new ArrayList<>();
                for(CharactersAbilities charactersAbilities: charactersAbilitiesService.getAllByCharacter(characters.get(i))){
                    abilities1.add(charactersAbilities);
                }
                characters.get(i).setAbilites(abilities1);
            }

            log.info("Characters");
            log.info("---------------------------------------");
            for(int i=0;i<characters.size();i++){
                log.info(characters.get(i).toString());
            }

            armyService.saveOrUpdate(new Army());
            armyService.saveOrUpdate(new Army());
            armyService.saveOrUpdate(new Army());
            armyService.saveOrUpdate(new Army());

            List<Army> armies = new ArrayList<>();

            for(Army army: armyService.getAll()){
                armies.add(army);
            }
            /*
            for(int i=0;i<4;i++){
                armies.add(new Army());
            }

            List<UnitsInArmy> unitsInArmies=new ArrayList<>();
                unitsInArmies.add(new UnitsInArmy(20,armies.get(0),units.get(0)));
                unitsInArmies.add(new UnitsInArmy(20,armies.get(1),units.get(1)));
                unitsInArmies.add(new UnitsInArmy(20,armies.get(2),units.get(0)));
                unitsInArmies.add(new UnitsInArmy(20,armies.get(3),units.get(0)));
                unitsInArmies.add(new UnitsInArmy(20,armies.get(3),units.get(1)));
*/

            unitsInArmyService.saveOrUpdate(new UnitsInArmy(20,armies.get(0),units.get(0)));
            unitsInArmyService.saveOrUpdate(new UnitsInArmy(20,armies.get(1),units.get(1)));
            unitsInArmyService.saveOrUpdate(new UnitsInArmy(20,armies.get(2),units.get(0)));
            unitsInArmyService.saveOrUpdate(new UnitsInArmy(10,armies.get(3),units.get(0)));
            unitsInArmyService.saveOrUpdate(new UnitsInArmy(10,armies.get(3),units.get(1)));


            /*for(int i=0;i<armies.size();i++){
                Collection<UnitsInArmy> units1=new ArrayList<>();
                for(UnitsInArmy unitsInArmy: unitsInArmyService.getAllByArmy(armies.get(i))){
                    units1.add(unitsInArmy);
                }
                armies.get(i).setUnits(units1);
                armies.get(i).calculatePower();
            }*/
            armies.clear();
            for(Army army: armyService.getAll()){
                armies.add(army);
            }

            log.info("Armies");
            log.info("---------------------------------------");
            for(int i=0;i<armies.size();i++){
                log.info(armies.get(i).toString());
            }

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

            usersService.saveOrUpdate(new Users("Need to create a nick",CryptWithMD5.cryptWithMD5("secretPassword"),"jabber1",3000,50.0,20,50,"пользователь",characters.get(0),cities.get(0),armies.get(0)));
            usersService.saveOrUpdate(new Users("Really need to create a nick",CryptWithMD5.cryptWithMD5("secretPassword"),"jabber2",5000,66.6666,15,30,"пользователь",characters.get(1),cities.get(1),armies.get(1)));

            List<Users> users=new ArrayList<>();
            for(Users user:usersService.getAll()){
                Collection<UnitsInArmy> units1=new ArrayList<>();
                for(UnitsInArmy unitsInArmy:unitsInArmyService.getAllByArmy(user.getArmy())){
                    units1.add(unitsInArmy);
                }
                user.getArmy().setUnits(units1);
                Collection<CharactersAbilities> abilities1=new ArrayList<>();
                for(CharactersAbilities charactersAbilities:charactersAbilitiesService.getAllByCharacter(user.getCharacter())){
                    abilities1.add(charactersAbilities);
                }
                user.getCharacter().setAbilites(abilities1);
                Collection<BuildingsInCities> buildings1=new ArrayList<>();
                for(BuildingsInCities buildingsInCities: buildingsInCitiesService.getAllByCity(user.getCity())){
                    buildings1.add(buildingsInCities);
                }
                user.getCity().setBuildings(buildings1);
                users.add(user);

            }
            log.info("Users");
            log.info("---------------------------------------");
            for(int i=0;i<users.size();i++){
                log.info(users.get(i).toString());
            }




			log.info("");
		};
	}

}
