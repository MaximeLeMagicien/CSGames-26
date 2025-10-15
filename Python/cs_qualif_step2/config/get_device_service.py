from cs_qualif_step2.core.application.device_service import DeviceService
from cs_qualif_step2.core.infra.in_memory_device_repository import InMemoryDeviceRepository
from cs_qualif_step2.core.domain.device.devicefactory import DeviceFactory

device_repository = InMemoryDeviceRepository()

def get_device_service() -> DeviceService:
    return DeviceService(device_repository, DeviceFactory())
