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
	public CommandLineRunner demo(UnitsService unitsService,RaceService raceService,BuildingsService buildingsService,CitiesService citiesService, CharactersService charactersService,AbilitiesService abilitiesService, ArmyService armyService, UnitsInArmyService unitsInArmyService, LocationsService locationsService,UsersService usersService ) {
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

            charactersService.saveOrUpdate(new Characters("Леголас","Лучник",races.get(1)));
            charactersService.saveOrUpdate(new Characters("Саурон","Маг",races.get(0)));

            List<Characters> characters = new ArrayList<>();
            for(Characters character:charactersService.getAll()){
                characters.add(character);
            }

            abilitiesService.saveOrUpdate(new Abilities("Выстрел","нанесение урона",0,2,50,characters.get(0)));
            abilitiesService.saveOrUpdate(new Abilities("Мощный выстрел","нанесение урона",3,4,150,characters.get(0)));
            abilitiesService.saveOrUpdate(new Abilities("Меткий выстрел","нанесение урона",9,7,500,characters.get(0)));
            abilitiesService.saveOrUpdate(new Abilities("Удар","нанесение урона",0,2,60,characters.get(1)));
            abilitiesService.saveOrUpdate(new Abilities("Удар Дубиной","нанесение урона",3,5,200,characters.get(1)));
            abilitiesService.saveOrUpdate(new Abilities("Сокрушающий удар","нанесение урона",9,9,630,characters.get(1)));

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
            log.info(charactersService.getByName("Леголас").toString());

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

            usersService.saveOrUpdate(new Users("Need to create a nick",CryptWithMD5.cryptWithMD5("secretPassword"),"jabber1",3000,50.0,20,50,"пользователь",characters.get(0),cities.get(0),armies.get(0)));
            usersService.saveOrUpdate(new Users("Really need to create a nick",CryptWithMD5.cryptWithMD5("secretPassword"),"jabber2",5000,66.6666,15,30,"пользователь",characters.get(1),cities.get(1),armies.get(1)));

            log.info("Users");
            log.info("---------------------------------------");
            for(Users user:usersService.getAll()){
                log.info(user.toString());
            }
			log.info("");
		};
	}

}
