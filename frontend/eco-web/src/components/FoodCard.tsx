
import React from 'react';
import { Clock, MapPin, Heart } from 'lucide-react';
import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';

interface FoodOffer {
  id: string;
  title: string;
  restaurant: string;
  originalPrice: number;
  discountedPrice: number;
  distance: string;
  timeLeft: string;
  image: string;
  category: string;
  pickupTime: string;
}

interface FoodCardProps {
  offer: FoodOffer;
  onReserve: (id: string) => void;
  onToggleFavorite: (id: string) => void;
  isFavorite?: boolean;
}

export const FoodCard: React.FC<FoodCardProps> = ({ 
  offer, 
  onReserve, 
  onToggleFavorite, 
  isFavorite = false 
}) => {
  const discountPercentage = Math.round(((offer.originalPrice - offer.discountedPrice) / offer.originalPrice) * 100);

  return (
    <Card className="overflow-hidden shadow-sm hover:shadow-md transition-shadow">
      <div className="relative">
        <img 
          src={`https://images.unsplash.com/${offer.image}?w=400&h=200&fit=crop`}
          alt={offer.title}
          className="w-full h-48 object-cover"
        />
        <button
          onClick={() => onToggleFavorite(offer.id)}
          className="absolute top-3 right-3 p-2 bg-white/90 rounded-full shadow-sm hover:bg-white transition-colors"
        >
          <Heart 
            size={20} 
            className={isFavorite ? 'fill-red-500 text-red-500' : 'text-gray-600'} 
          />
        </button>
        <Badge className="absolute top-3 left-3 bg-orange-500 hover:bg-orange-600">
          -{discountPercentage}%
        </Badge>
      </div>
      
      <CardContent className="p-4">
        <div className="flex justify-between items-start mb-2">
          <div>
            <h3 className="font-semibold text-gray-900 line-clamp-1">{offer.title}</h3>
            <p className="text-sm text-gray-600">{offer.restaurant}</p>
          </div>
          <Badge variant="outline" className="text-xs">
            {offer.category}
          </Badge>
        </div>
        
        <div className="flex items-center gap-4 text-sm text-gray-500 mb-3">
          <div className="flex items-center gap-1">
            <MapPin size={16} />
            <span>{offer.distance}</span>
          </div>
          <div className="flex items-center gap-1">
            <Clock size={16} />
            <span>{offer.timeLeft}</span>
          </div>
        </div>
        
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2">
            <span className="text-lg font-bold text-green-600">
              {offer.discountedPrice} MDL
            </span>
            <span className="text-sm text-gray-500 line-through">
              {offer.originalPrice} MDL
            </span>
          </div>
          <Button 
            onClick={() => onReserve(offer.id)}
            className="bg-green-600 hover:bg-green-700 text-white px-6"
          >
            Reserve
          </Button>
        </div>
        
        <p className="text-xs text-gray-500 mt-2">
          Pickup: {offer.pickupTime}
        </p>
      </CardContent>
    </Card>
  );
};
