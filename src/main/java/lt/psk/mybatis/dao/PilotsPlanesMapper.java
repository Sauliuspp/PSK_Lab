package lt.psk.mybatis.dao;

import java.util.List;
import lt.psk.mybatis.model.PilotsPlanes;
import org.mybatis.cdi.Mapper;

@Mapper
public interface PilotsPlanesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PILOTS_PLANES
     *
     * @mbg.generated Mon Apr 05 21:48:31 EEST 2021
     */
    int insert(PilotsPlanes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PILOTS_PLANES
     *
     * @mbg.generated Mon Apr 05 21:48:31 EEST 2021
     */
    List<PilotsPlanes> selectAll();
}