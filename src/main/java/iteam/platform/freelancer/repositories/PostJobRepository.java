package iteam.platform.freelancer.repositories;

import iteam.platform.freelancer.entities.Postjob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostJobRepository extends JpaRepository<Postjob, UUID> {


    @Query(value = """
        SELECT 
            p.id as id,
            p.jobd as jobd,
            p.jtittle as jtittle,
            p.jskills as jskills,
            p.jtype as jtype,
            p.jsalary as jsalary,
            p.jcname as jcname,
            p.jcemail as jcemail,
            c.profilec as profileimg,
            c.about as about
        FROM postjob p
        RIGHT JOIN company c ON p.jcname = c.name
        WHERE p.jcname IS NOT NULL AND c.name IS NOT NULL AND p.id IS NOT NULL
        ORDER BY RANDOM()
        """, nativeQuery = true)
    List<Object[]> findRandomJobsWithCompanyInfoNative();

    @Query(value = """
    SELECT 
        p.id as id,
        p.jobd as jobd,
        p.jtittle as jtittle,
        p.jskills as jskills,
        p.jtype as jtype,
        p.jsalary as jsalary,
        p.jcname as jcname,
        p.jcemail as jcemail,
        c.profilec as profileimg,
        c.about as about
    FROM postjob p
    RIGHT JOIN company c ON p.jcname = c.name
    WHERE p.id = :id
    """, nativeQuery = true)
    List<Object[]> findJobWithCompanyById(@Param("id") UUID id);







}
